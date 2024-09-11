package com.aliza.alizaShop.config.filters

import com.aliza.alizaShop.config.JwtTokenUtils
import com.aliza.alizaShop.dto.UserDto
import com.aliza.alizaShop.services.customers.UserService
import com.aliza.alizaShop.utils.JwtTokenException
import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtRequestFilter : Filter {

    @Autowired
    private lateinit var jwtTokenUtil: JwtTokenUtils

    @Autowired
    private lateinit var userService: UserService

    private val excludeUrls = ArrayList<String>()

    private val excludeContainsUrls = ArrayList<String>()

    init {
        excludeUrls.add("/api/user/login")
        excludeUrls.add("/api/user/register")
        excludeUrls.add("/api/trx/gotoPayment")
        excludeUrls.add("/api/trx/verify")

        excludeContainsUrls.add("/api/color")
        excludeContainsUrls.add("/api/productCategory")
        excludeContainsUrls.add("/api/product")
        excludeContainsUrls.add("/api/blog")
        excludeContainsUrls.add("/api/content")
        excludeContainsUrls.add("/api/slider")
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        try {
            val url = (request as HttpServletRequest).requestURI.lowercase(Locale.getDefault())
            if (excludeUrls.stream().anyMatch { x -> url == x.lowercase(Locale.getDefault()) } ||
                excludeContainsUrls.stream().anyMatch { x -> url.startsWith(x.lowercase(Locale.getDefault())) }) {
                chain!!.doFilter(request, response)
                return
            }
            val requestTokenHeader = request.getHeader("Authorization")
            if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer "))
                throw JwtTokenException("request token header does not set")
            val token = requestTokenHeader.substring(7)
            val username: String = jwtTokenUtil.getUsernameFromToken(token)
                ?: throw JwtTokenException("username can not resolve")
            val userVM = UserDto(userService.getByUsername(username)!!)
            if (!jwtTokenUtil.validateToken(token, userVM))
                throw JwtTokenException("invalid token")
            chain!!.doFilter(request, response)
        } catch (ex: JwtTokenException) {
            (response as HttpServletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
        } catch (ex: ExpiredJwtException) {
            (response as HttpServletResponse).sendError(HttpServletResponse.SC_EXPECTATION_FAILED, ex.message)
        } catch (ex: Exception) {
            (response as HttpServletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.message)
        }
    }
}