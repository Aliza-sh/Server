package com.aliza.alizaShop.controllers.invoices

import com.aliza.alizaShop.models.invoices.Invoice
import com.aliza.alizaShop.services.invoices.InvoiceService
import com.aliza.alizaShop.utils.NotFoundException
import com.aliza.alizaShop.utils.ServiceResponse
import jakarta.servlet.http.HttpServletRequest
import com.aliza.alizaShop.config.JwtTokenUtils
import com.aliza.alizaShop.utils.UserUtil
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/invoice")
@Tag(name="Invoice")
class InvoiceController {

    @Autowired
    private lateinit var service: InvoiceService

    @Autowired
    private lateinit var jwtUtil: JwtTokenUtils

    @GetMapping("/user/{userId}")
    fun getAllByUserId(
        @PathVariable userId: Long,
        @RequestParam pageSize: Int,
        @RequestParam pageIndex: Int,
        request: HttpServletRequest
    ): ServiceResponse<List<Invoice>> {
        return try {
            val currentUser = UserUtil.getCurrentUsername(jwtUtil, request)
            val data = service.getAllByUserId(userId, pageIndex, pageSize, currentUser)
                ?: throw NotFoundException("data not found")
            ServiceResponse(data, HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND, message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = e.message!!)
        }
    }

    @GetMapping("/{id}")
    fun getById(
        @PathVariable id: Long,
        request: HttpServletRequest
    ): ServiceResponse<Invoice> {
        return try {
            val currentUser = UserUtil.getCurrentUsername(jwtUtil, request)
            val data = service.getById(id,currentUser) ?: throw NotFoundException("data not found")
            ServiceResponse(data, HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND, message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = e.message!!)
        }
    }

    @PostMapping("")
    fun addInvoice(
        @RequestBody invoice: Invoice,
        request: HttpServletRequest
    ): ServiceResponse<Invoice> {
        return try {
            val currentUser = UserUtil.getCurrentUsername(jwtUtil, request)
            val data = service.insert(invoice, currentUser)
            ServiceResponse(data, HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND, message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = e.message!!)
        }
    }

}