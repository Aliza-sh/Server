package com.aliza.alizaShop.repositories.products

import com.aliza.alizaShop.models.products.Product
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : PagingAndSortingRepository<Product, Long>,CrudRepository<Product, Long> {
    override fun findAll(): List<Product>

    //select top 6 * from products order by addDate desc
    fun findTop6ByOrderByAddDateDesc(): List<Product>

    //select top 6 * from products order by visitCount desc
    fun findTop6ByOrderByVisitCountDesc(): List<Product>

    @Query("from Product where id in :idList")
    fun findAllByIdList(idList: List<Long>): List<Product>
}