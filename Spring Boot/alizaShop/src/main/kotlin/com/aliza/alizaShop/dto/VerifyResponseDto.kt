package com.aliza.alizaShop.dto

data class VerifyResponseDto(
    val status: String,
    val referenceId: String,
    val invoiceNumber: Long,
    val code: Int
)