package com.aliza.alizaShop.utils

import org.springframework.http.HttpStatus
import java.io.Serializable

data class ServiceResponse<T>(
    var data: T? = null,
    var status: HttpStatus,
    var message: String? = null,
    var totalCount: Long? = null
) : Serializable