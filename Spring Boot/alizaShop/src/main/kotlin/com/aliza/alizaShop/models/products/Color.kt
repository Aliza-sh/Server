package com.aliza.alizaShop.models.products

import jakarta.persistence.*

@Entity
data class Color(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    val title: String = "",
    val hexValue: String = "",
)