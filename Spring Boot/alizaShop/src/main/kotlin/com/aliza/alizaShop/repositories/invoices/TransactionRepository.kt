package com.aliza.alizaShop.repositories.invoices

import com.aliza.alizaShop.models.invoices.Transaction
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : PagingAndSortingRepository<Transaction, Long>, CrudRepository<Transaction, Long> {
}