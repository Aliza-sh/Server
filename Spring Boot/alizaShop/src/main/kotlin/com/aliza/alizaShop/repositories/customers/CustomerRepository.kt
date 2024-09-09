package com.aliza.alizaShop.repositories.customers

import com.aliza.alizaShop.models.customers.Customer
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : PagingAndSortingRepository<Customer, Long>, CrudRepository<Customer, Long> {
}