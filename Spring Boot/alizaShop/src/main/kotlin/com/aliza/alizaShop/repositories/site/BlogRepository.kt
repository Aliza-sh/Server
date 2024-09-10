package com.aliza.alizaShop.repositories.site

import com.aliza.alizaShop.models.site.Blog
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface BlogRepository : PagingAndSortingRepository<Blog, Long>, CrudRepository<Blog, Long> {
    override fun findAll(): List<Blog>
}