package com.aliza.alizaShop.models.invoices

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import com.aliza.alizaShop.models.products.Product

@Entity
data class InvoiceItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product? = null,

    var quantity: Int = 0,
    var unitPrice: Long = 0,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    var invoice: Invoice? = null
)
