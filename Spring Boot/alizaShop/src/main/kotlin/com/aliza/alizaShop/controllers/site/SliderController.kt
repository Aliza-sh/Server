package com.aliza.alizaShop.controllers.site

import com.aliza.alizaShop.models.site.Slider
import com.aliza.alizaShop.services.site.SliderService
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
@RequestMapping("api/slider")
@Tag(name = "Slider")
class SliderController {

    @Autowired
    private lateinit var service: SliderService

    @SecurityRequirements()
    //http://localhost:8080/api/slider
    @GetMapping("")
    fun getAll(): ServiceResponse<List<Slider>> {
        return try {
            ServiceResponse(service.getAll(), HttpStatus.OK)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = e.message)
        }
    }

    @SecurityRequirements()
    //http://localhost:8080/api/slider/1
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ServiceResponse<Slider> {
        return try {
            val data = service.getById(id) ?: throw NotFoundException("data not found")
            ServiceResponse(data, HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND, message = e.message)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = e.message)
        }
    }
}