package com.aliza.alizaShop.models.customers

import com.fasterxml.jackson.annotation.JsonIgnore
import com.aliza.alizaShop.models.invoices.Invoice
import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var username: String = "",
    var password: String = "",

    @OneToOne
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null,

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    var invoices: Set<Invoice>? = null
)