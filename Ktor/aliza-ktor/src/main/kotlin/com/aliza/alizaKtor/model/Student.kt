package com.aliza.alizaKtor.model

import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val code: String
)