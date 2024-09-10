package com.aliza.alizaShop.services.product

import com.aliza.alizaShop.models.products.Product
import com.aliza.alizaShop.repositories.products.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class ProductService {

    @Autowired
    lateinit var repository: ProductRepository

    fun getById(id: Long): Product? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getAll(): List<Product> {
        return repository.findAll()
    }

    fun getAll(pageIndex: Int, pageSize: Int): List<Product> {
        val pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by("id"))
        return repository.findAll(pageRequest).toList()
    }

    fun getNewProducts(): List<Product> {
        return repository.findTop6ByOrderByAddDateDesc()
    }

    fun getPopularProducts(): List<Product> {
        return repository.findTop6ByOrderByVisitCountDesc()
    }

    fun getAllCount(): Long {
        return repository.count()
    }

}