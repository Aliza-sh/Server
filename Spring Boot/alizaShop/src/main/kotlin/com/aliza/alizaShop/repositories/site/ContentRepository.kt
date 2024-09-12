package com.aliza.alizaShop.repositories.site

import com.aliza.alizaShop.models.site.Content
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ContentRepository : PagingAndSortingRepository<Content, Long>, CrudRepository<Content, Long> {
    override fun findAll(): List<Content>
    fun findFirstByTitle(title: String): Content?
}