package com.aliza.alizaShop.repositories.products

import com.aliza.alizaShop.models.products.Color
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ColorRepository : PagingAndSortingRepository<Color, Long>, CrudRepository<Color, Long> {
}