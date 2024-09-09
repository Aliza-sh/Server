package com.aliza.alizaShop.models.products

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*


@Entity
data class ProductCategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    val title: String = "",
    val image: String = "",

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    val products: Set<Product>? = null
)
