package com.aliza.alizaShop.repositories.site

import com.aliza.alizaShop.models.site.Slider
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface SliderRepository : PagingAndSortingRepository<Slider, Long>, CrudRepository<Slider, Long> {
}