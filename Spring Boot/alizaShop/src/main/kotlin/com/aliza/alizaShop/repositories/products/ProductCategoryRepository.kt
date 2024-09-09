package com.aliza.alizaShop.repositories.products

import com.aliza.alizaShop.models.products.ProductCategory
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductCategoryRepository : PagingAndSortingRepository<ProductCategory, Long>,
    CrudRepository<ProductCategory, Long> {
}