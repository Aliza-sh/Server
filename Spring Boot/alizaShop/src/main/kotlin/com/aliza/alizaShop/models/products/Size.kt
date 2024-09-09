package com.aliza.alizaShop.models.products

import jakarta.persistence.*

@Entity
data class Size(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    val title: Int = 0,
)