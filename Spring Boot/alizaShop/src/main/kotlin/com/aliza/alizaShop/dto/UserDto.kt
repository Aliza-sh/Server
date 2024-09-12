package com.aliza.alizaShop.dto

import com.aliza.alizaShop.models.customers.Customer
import com.aliza.alizaShop.models.customers.User

data class UserDto(
    var id: Long = 0,
    var username: String = "",
    var oldPassword: String = "",
    var password: String = "",
    var repeatPassword: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var phone: String = "",
    var postalCode: String = "",
    var customerId: Long = 0,
    var token: String = ""
) {

    constructor(user: User) : this(
        id = user.id,
        username = user.username,
        firstName = user.customer!!.firstName,
        lastName = user.customer!!.lastName,
        address = user.customer!!.address,
        phone = user.customer!!.phone,
        postalCode = user.customer!!.postalCode,
        customerId = user.customer!!.id
    )

    fun convertToUser(): User {
        return User(
            id,
            username,
            password,
            convertToCustomer()
        )
    }

    private fun convertToCustomer(): Customer {
        return Customer(
            customerId,
            firstName,
            lastName,
            address,
            phone,
            postalCode
        )
    }
}