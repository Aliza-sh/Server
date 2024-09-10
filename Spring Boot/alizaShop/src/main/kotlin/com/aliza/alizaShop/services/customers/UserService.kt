package com.aliza.alizaShop.services.customers

import com.aliza.alizaShop.models.customers.User
import com.aliza.alizaShop.repositories.customers.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    lateinit var repository: UserRepository

    fun insert(data: User): User {
        return repository.save(data)
    }

    fun update(data: User): User? {
        val oldData = getById(data.id) ?: return null
        oldData.password = data.password
        return repository.save(oldData)
    }

    fun getById(id: Long): User? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getUserByUserAndPas(user: String, password: String): User? {
        return repository.findFirstByUsernameAndPassword(user, password)
    }

}