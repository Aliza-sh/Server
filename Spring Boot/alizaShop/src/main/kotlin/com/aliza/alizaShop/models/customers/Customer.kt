package com.aliza.alizaShop.models.customers

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var phone: String = "",
    var postalCode: String = "",

    @JsonIgnore
    @OneToOne(mappedBy = "customer")
    var user: User? = null
)