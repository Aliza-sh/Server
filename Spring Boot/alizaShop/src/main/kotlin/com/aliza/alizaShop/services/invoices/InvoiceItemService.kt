package com.aliza.alizaShop.services.invoices

import com.aliza.alizaShop.models.invoices.InvoiceItem
import com.aliza.alizaShop.repositories.invoices.InvoiceItemRepository
import com.aliza.alizaShop.services.product.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InvoiceItemService {

    @Autowired
    private lateinit var repository: InvoiceItemRepository

    @Autowired
    private lateinit var productService: ProductService

    fun getById(id: Long): InvoiceItem? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getAllByInvoiceId(invoiceId: Int): List<InvoiceItem> {
        return repository.findAllByInvoiceId(invoiceId).toList()
    }

}