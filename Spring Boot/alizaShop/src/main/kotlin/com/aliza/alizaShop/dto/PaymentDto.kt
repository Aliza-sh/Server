package com.aliza.alizaShop.dto

import com.aliza.alizaShop.models.invoices.InvoiceItem

data class PaymentDto(
    val user: UserDto,
    val items: List<InvoiceItem>
)