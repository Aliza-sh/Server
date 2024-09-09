package com.aliza.alizaShop.models.invoices

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    val invoice: Invoice? = null
)
