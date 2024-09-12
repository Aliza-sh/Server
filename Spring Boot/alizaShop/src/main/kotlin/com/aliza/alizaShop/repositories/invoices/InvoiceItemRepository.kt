package com.aliza.alizaShop.repositories.invoices

import com.aliza.alizaShop.models.invoices.InvoiceItem
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface InvoiceItemRepository : PagingAndSortingRepository<InvoiceItem, Long>, CrudRepository<InvoiceItem, Long> {
    @Query("from InvoiceItem where invoice.id = :invoiceId")
    fun findAllByInvoiceId(invoiceId: Int): List<InvoiceItem>
}