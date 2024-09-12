package com.aliza.alizaShop.models.transactions.nextpay

data class VerifyRequest(
    val apiKey: String,
    val transId: String,
    val amount: Int
)