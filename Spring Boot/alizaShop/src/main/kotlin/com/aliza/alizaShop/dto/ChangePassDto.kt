package com.aliza.alizaShop.dto

data class ChangePassDto(
    val userName: String,
    var oldPassword: String,
    var password: String,
    var repeatPassword: String,
)
