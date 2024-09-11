package com.aliza.alizaShop.controllers.customers

import com.aliza.alizaShop.models.customers.User
import com.aliza.alizaShop.services.customers.UserService
import com.aliza.alizaShop.utils.NotFoundException
import com.aliza.alizaShop.utils.ServiceResponse
import jakarta.servlet.http.HttpServletRequest
import com.aliza.alizaShop.config.JwtTokenUtils
import com.aliza.alizaShop.dto.ChangePassDto
import com.aliza.alizaShop.dto.LoginDto
import com.aliza.alizaShop.dto.RegisterDto
import com.aliza.alizaShop.dto.UserDto
import com.aliza.alizaShop.utils.UserUtil.Companion.getCurrentUsername
import io.swagger.v3.oas.annotations.security.SecurityRequirements
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/user")
@Tag(name="User Auth")
class UserController {

    @Autowired
    private lateinit var service: UserService

    @Autowired
    private lateinit var jwtUtil: JwtTokenUtils

    @GetMapping("")
    fun getById(request: HttpServletRequest): ServiceResponse<User> {
        return try {
            val currentUser = getCurrentUsername(jwtUtil, request)
            val data = service.getByUsername(currentUser) ?: throw NotFoundException("data not found")
            ServiceResponse(data, HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND, message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = e.message!!)
        }
    }

    @SecurityRequirements()
    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDto): ServiceResponse<UserDto> {
        return try {
            val data =
                service.getUserByUserAndPas(loginDto.username, loginDto.password) ?: throw NotFoundException("data not found")
            val userDto = UserDto(data)
            userDto.token = jwtUtil.generateToken(userDto)!!
            ServiceResponse(userDto, HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND, message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = e.message!!)
        }
    }

    @SecurityRequirements()
    @PostMapping("/register")
    fun addUser(@RequestBody user: RegisterDto): ServiceResponse<User> {
        return try {
            val data = service.insert(user.convertToUser())
            ServiceResponse(data, HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND, message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = e.message!!)
        }
    }

    @PutMapping("/update")
    fun editUser(@RequestBody user: UserDto, request: HttpServletRequest): ServiceResponse<User> {
        return try {
            val currentUser = getCurrentUsername(jwtUtil, request)
            val data =
                service.update(user.convertToUser(), currentUser) ?: throw NotFoundException("data not found")
            ServiceResponse(data, HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND, message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = e.message!!)
        }
    }

    @PutMapping("/changePassword")
    fun changePassword(@RequestBody user: ChangePassDto, request: HttpServletRequest): ServiceResponse<User> {
        return try {
            val currentUser = getCurrentUsername(jwtUtil, request)
            val data =
                service.changePassword(user.userName,user.password, user.oldPassword, user.repeatPassword, currentUser)
                    ?: throw NotFoundException("data not found")
            ServiceResponse(data, HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(status = HttpStatus.NOT_FOUND, message = e.message!!)
        } catch (e: Exception) {
            ServiceResponse(status = HttpStatus.INTERNAL_SERVER_ERROR, message = e.message!!)
        }
    }
}