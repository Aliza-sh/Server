package com.aliza.alizaShop.models.site

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.GenerationType


@Entity
data class Blog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    val image: String = "",
    val title: String = "",
    val subTitle: String = "",
    val description: String = "",
    val visitCount: Int = 0,
    val addDate: String = "",
)
