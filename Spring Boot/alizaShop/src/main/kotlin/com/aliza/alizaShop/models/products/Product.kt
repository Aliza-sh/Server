package com.aliza.alizaShop.models.products

import org.hibernate.annotations.ColumnDefault
import jakarta.persistence.*

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    val title: String = "",
    val image: String = "",
    val visitCount: Int = 0,
    val addDate: String = "",
    val description: String = "",
    @ColumnDefault(value = "0")
    val price: Long = 0,

    @ManyToMany
    val colors: Set<Color>? = null,

    @ManyToOne()
    val category: ProductCategory? = null,

    @ManyToMany
    val sizes: Set<Size>? = null,
)
