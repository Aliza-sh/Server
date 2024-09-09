package com.aliza.alizaShop.repositories.invoices

import com.aliza.alizaShop.models.invoices.Invoice
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface InvoiceRepository : PagingAndSortingRepository<Invoice, Long>, CrudRepository<Invoice, Long> {
}