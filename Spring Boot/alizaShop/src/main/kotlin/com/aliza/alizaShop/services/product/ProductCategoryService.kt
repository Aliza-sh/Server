package com.aliza.alizaShop.services.product

import com.aliza.alizaShop.models.products.ProductCategory
import com.aliza.alizaShop.repositories.products.ProductCategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductCategoryService {

    @Autowired
    lateinit var repository: ProductCategoryRepository

    fun getById(id: Long): ProductCategory? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getAll(): List<ProductCategory> {
        return repository.findAll()
    }

    fun getAllCount(): Long {
        return repository.count()
    }
}