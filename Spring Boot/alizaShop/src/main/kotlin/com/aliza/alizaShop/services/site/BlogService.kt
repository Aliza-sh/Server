package com.aliza.alizaShop.services.site

import com.aliza.alizaShop.models.site.Blog
import com.aliza.alizaShop.repositories.site.BlogRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class BlogService {

    @Autowired
    lateinit var repository: BlogRepository

    fun getById(id: Long): Blog? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getAll(): List<Blog> {
        return repository.findAll()
    }

    fun getAll(pageIndex: Int, pageSize: Int): List<Blog> {
        //total : 587 blog
        //get : 10 blog (pageSize)
        //get : 1-10 [0]
        //get : 11-20 [1]
        //get : 21-30 [2]
        //...
        //get : 578-587 [n]
        //blogs.splice(pageIndex * pageSize,(pageIndex * pageSize) + pageSize)
        //pageIndex = 0, pageSize= 10
        //0,10
        //pageIndex = 1, pageSize= 10
        //10-20
        //pageIndex = 2, pageSize= 10
        //20-30
        val pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by("id"))
        return repository.findAll(pageRequest).toList()
    }

    fun getAllCount(): Long {
        return repository.count()
    }
}