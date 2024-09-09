package com.aliza.alizaShop.repositories.invoices

import com.aliza.alizaShop.models.invoices.InvoiceItem
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface InvoiceItemRepository : PagingAndSortingRepository<InvoiceItem, Long>, CrudRepository<InvoiceItem, Long> {
}