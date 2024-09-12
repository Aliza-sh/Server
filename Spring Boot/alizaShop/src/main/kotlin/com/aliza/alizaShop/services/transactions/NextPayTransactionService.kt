package com.aliza.alizaShop.services.transactions

import com.aliza.alizaShop.config.payment.NextPayConfig
import com.aliza.alizaShop.dto.PaymentDto
import com.aliza.alizaShop.dto.VerifyResponseDto
import com.aliza.alizaShop.models.customers.User
import com.aliza.alizaShop.models.invoices.Invoice
import com.aliza.alizaShop.models.invoices.Transaction
import com.aliza.alizaShop.models.products.Product
import com.aliza.alizaShop.models.transactions.nextpay.TokenRequest
import com.aliza.alizaShop.models.transactions.nextpay.TokenResponse
import com.aliza.alizaShop.models.transactions.nextpay.VerifyRequest
import com.aliza.alizaShop.models.transactions.nextpay.VerifyResponse
import com.aliza.alizaShop.services.customers.UserService
import com.aliza.alizaShop.services.invoices.InvoiceService
import com.aliza.alizaShop.services.invoices.TransactionService
import com.aliza.alizaShop.services.product.ProductService
import com.aliza.alizaShop.utils.NotFoundException
import com.aliza.alizaShop.utils.payment.NextPayStatusCodeUtil
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class NextPayTransactionService {

    @Autowired
    private lateinit var configuration: NextPayConfig

    @Autowired
    private lateinit var trxService: TransactionService

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var invoiceService: InvoiceService

    @Autowired
    private lateinit var productService: ProductService

    @Autowired
    private lateinit var transactionService: TransactionService

    fun getPaymentUri(data: PaymentDto): String {

        /*
            1) check user exists?
            2) if(!userExists) then register user
            3) create invoice
            4) get payment url
            5) create trx
         */
        checkValidation(data)

        var user: User? = registerUser(data)
        if (user == null) {
            user = userService.getById(data.user.id)
            userService.update(data.user.convertToUser(), user!!.username)
        }

        val currentUser = user.username

        val invoice = createInvoice(user, data, currentUser)

        val productList = getProducts(data)

        var amount: Long = getTotalAmount(data, productList)

        val response = preparePaymentUrl(data, amount, invoice)
        val trx = Transaction(
            transId = response.transId,
            code = response.code,
            amount = amount.toInt(),
            customerPhone = data.user.phone,
            invoice = invoice,
            custom = NextPayStatusCodeUtil.getMessage(response.code)
        )
        trxService.insert(trx)
        if (response.code != -1)
            throw Exception(NextPayStatusCodeUtil.getMessage(response.code))
        return "${configuration.paymentUri}${response.transId}"
    }

    fun verifyPayment(transId: String, orderId: String, amount: Int): VerifyResponseDto {
        val trx = transactionService.getByTransId(transId) ?: throw NotFoundException("Transaction not found!")

        val response = doVerifyPayment(orderId, trx)

        trx.code = response.code!!
        trx.custom = NextPayStatusCodeUtil.getMessage(response.code!!)

        if(trx.code == 0) {
            trx.refId = response.ShaparakRefId!!
        }

        transactionService.update(trx)

        return VerifyResponseDto(
            NextPayStatusCodeUtil.getMessage(response.code!!),
            trx.refId,
            trx.invoice!!.id,
            trx.code
        )
    }

    private fun doVerifyPayment(trans_id: String, trx: Transaction): VerifyResponse {
        val mapper = jacksonObjectMapper()
        val url = configuration.verifyUri
        val restClient = RestTemplate()
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }
        val request = VerifyRequest(configuration.apikey, trans_id, trx.amount)

        val resp = restClient.postForEntity(url, HttpEntity(request, headers), String::class.java)
        if (resp.statusCode.is2xxSuccessful) {
            return mapper.readValue(resp.body!!)
        } else {
            throw IllegalStateException()
        }
    }

    //region helper methods
    private fun getTotalAmount(
        data: PaymentDto,
        productList: List<Product>
    ): Long {
        var amount: Long = 0
        data.items.forEach { item ->
            val product = productList.first { x -> x.id == item.product!!.id }
            amount += product.price * item.quantity
        }
        return amount
    }

    private fun getProducts(data: PaymentDto): List<Product> {
        val idList = ArrayList<Long>()
        data.items.forEach {
            idList.add(it.product!!.id)
        }
        val productList = productService.getAllByIdList(idList)
        return productList
    }

    private fun createInvoice(
        user: User?,
        data: PaymentDto,
        currentUser: String
    ): Invoice {
        val invoice = Invoice(
            user = user,
            items = data.items
        )
        invoiceService.insert(invoice, currentUser)
        return invoice
    }

    private fun registerUser(data: PaymentDto): User? {
        if (data.user.id <= 0) {
            return userService.insert(data.user.convertToUser())
        }
        return null
    }

    private fun checkValidation(data: PaymentDto) {
        if (data?.user == null || data?.items == null)
            throw Exception("Invalid input data")
    }

    private fun preparePaymentUrl(data: PaymentDto, amount: Long, invoice: Invoice): TokenResponse {
        val mapper = jacksonObjectMapper()
        val url = configuration.tokenEndPoint
        val restClient = RestTemplate()
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }

        val request = TokenRequest(
            apiKey = configuration.apikey,
            orderId = invoice.id.toString(),
            amount = amount.toInt(),
            customerPhone = data.user.phone,
            callbackUri = configuration.callbackUri,
        )

        val resp = restClient.postForEntity(url, HttpEntity(request, headers), String::class.java)
        if (resp.statusCode.is2xxSuccessful) {
            return mapper.readValue(resp.body!!)
        } else {
            throw IllegalStateException()
        }
    }
    //endregion
}