package com.aliza.alizaShop.services.site

import com.aliza.alizaShop.models.site.Content
import com.aliza.alizaShop.repositories.site.ContentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ContentService {

    @Autowired
    lateinit var repository: ContentRepository

    fun getById(id: Long): Content? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getAll(): List<Content> {
        return repository.findAll()
    }

    fun getByTitle(title: String): Content? {
        return repository.findFirstByTitle(title)
    }

    fun getAllCount(): Long {
        return repository.count()
    }
}