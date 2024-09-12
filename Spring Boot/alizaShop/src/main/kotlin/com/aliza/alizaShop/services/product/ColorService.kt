package com.aliza.alizaShop.services.product

import com.aliza.alizaShop.models.products.Color
import com.aliza.alizaShop.repositories.products.ColorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ColorService {

    @Autowired
    lateinit var repository: ColorRepository

    fun getById(id: Long): Color? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getAll(): List<Color> {
        return repository.findAll()
    }

    fun getAllCount(): Long {
        return repository.count()
    }
}