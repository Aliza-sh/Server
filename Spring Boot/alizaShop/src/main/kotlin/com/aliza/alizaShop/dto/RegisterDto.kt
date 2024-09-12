package com.aliza.alizaShop.dto

import com.aliza.alizaShop.models.customers.Customer
import com.aliza.alizaShop.models.customers.User

data class RegisterDto(
    var firstName: String,
    var lastName: String,
    var username: String,
    var password: String,
    var phone: String,
){
    fun convertToUser(): User {
        return User(
            username = username,
            password = password,
            customer = convertToCustomer()
        )
    }

    private fun convertToCustomer(): Customer {
        return Customer(
            firstName = firstName,
            lastName = lastName,
            phone = phone,
        )
    }
}
