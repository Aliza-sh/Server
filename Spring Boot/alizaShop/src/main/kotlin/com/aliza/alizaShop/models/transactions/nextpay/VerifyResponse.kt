package com.aliza.alizaShop.models.transactions.nextpay

data class VerifyResponse(
    var code: Int?,
    var amount: Int?,
    var orderId: String?,
    var cardHolder: String?,
    var customerPhone: String?,
    var ShaparakRefId: String?,
    var custom: String?
)