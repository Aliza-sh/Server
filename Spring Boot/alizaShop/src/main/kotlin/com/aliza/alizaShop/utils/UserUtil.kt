package com.aliza.alizaShop.utils

import com.aliza.alizaShop.config.JwtTokenUtils
import jakarta.servlet.http.HttpServletRequest
import java.util.*

class UserUtil {
    companion object {
        fun getCurrentUsername(jwtUtil: JwtTokenUtils, request: HttpServletRequest): String {
            val header = request.getHeader("Authorization")
            if (header == null || !header.lowercase(Locale.getDefault())
                    .startsWith("bearer")
            )
                throw JwtTokenException("please set bearer token")
            val token = header.substring(7)
            return jwtUtil.getUsernameFromToken(token)
        }
    }
}