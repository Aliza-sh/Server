package com.aliza.alizaShop.controllers.transactions

import com.aliza.alizaShop.dto.PaymentDto
import com.aliza.alizaShop.dto.VerifyResponseDto
import com.aliza.alizaShop.services.transactions.NextPayTransactionService
import com.aliza.alizaShop.utils.ServiceResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirements
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/trx")
@Tag(name = "Transaction")
class TransactionController {

    @Autowired
    private lateinit var nextPayTransactionService: NextPayTransactionService

    @SecurityRequirements
    @PostMapping("/gotoPayment")
    fun gotoPayment(@RequestBody data: PaymentDto, request: HttpServletRequest): ServiceResponse<String> {
        return try {
            ServiceResponse(nextPayTransactionService.getPaymentUri(data), HttpStatus.OK)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = e.message!!)
        }
    }

    @SecurityRequirements
    @GetMapping("/verify")
    fun verify(
        @RequestParam transId: String,
        @RequestParam orderId: String,
        @RequestParam amount: Int
    ): ServiceResponse<VerifyResponseDto> {
        return try {
            ServiceResponse(nextPayTransactionService.verifyPayment(transId, orderId, amount), HttpStatus.OK)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = e.message!!)
        }
    }

}