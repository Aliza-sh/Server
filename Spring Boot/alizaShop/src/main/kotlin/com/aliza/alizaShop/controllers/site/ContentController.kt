package com.aliza.alizaShop.controllers.site

import com.aliza.alizaShop.models.site.Content
import com.aliza.alizaShop.services.site.ContentService
import com.aliza.alizaShop.utils.NotFoundException
import com.aliza.alizaShop.utils.ServiceResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirements
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/content")
@Tag(name = "Content")
class ContentController {

    @Autowired
    private lateinit var service: ContentService

    @SecurityRequirements()
    @GetMapping("")
    fun getAll(): ServiceResponse<List<Content>> {
        return try {
            ServiceResponse(service.getAll(), HttpStatus.OK)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = e.message!!)
        }
    }

    @SecurityRequirements()
    @GetMapping("/{title}")
    fun getByTitle(@PathVariable title: String): ServiceResponse<List<Content>> {
        return try {
            val data = service.getByTitle(title) ?: throw NotFoundException("data not found")
            ServiceResponse(listOf(data), HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND, message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = e.message!!)
        }
    }
}