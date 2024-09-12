package com.aliza.alizaShop.models.transactions.nextpay

data class TokenRequest(
    val apiKey: String,
    val orderId: String,
    val amount: Int,
    val callbackUri: String,
    val currency: String = "IRT",
    val customerPhone: String,
    val autoVerify: String = "no"
)