package com.aliza.alizaShop.repositories.customers

import com.aliza.alizaShop.models.customers.User
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : PagingAndSortingRepository<User, Long>, CrudRepository<User, Long> {
    fun findFirstByUsernameAndPassword(username: String, password: String): User?
}