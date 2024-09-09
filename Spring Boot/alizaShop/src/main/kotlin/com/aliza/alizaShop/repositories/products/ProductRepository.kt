package com.aliza.alizaShop.repositories.products

import com.aliza.alizaShop.models.products.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : PagingAndSortingRepository<Product, Long>,CrudRepository<Product, Long> {
}