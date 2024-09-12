package com.aliza.alizaShop.models.transactions.nextpay

data class TokenResponse(
    val code: Int,
    val transId: String,
    val amount: Int
)