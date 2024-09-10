package com.aliza.alizaShop.services.customers

import com.aliza.alizaShop.models.customers.Customer
import com.aliza.alizaShop.repositories.customers.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerService {

    @Autowired
    private lateinit var repository: CustomerRepository

    fun insert(data: Customer): Customer {
        return repository.save(data)
    }

    fun update(data: Customer): Customer? {
        val oldData = getById(data.id) ?: return null
        oldData.address = data.address
        oldData.firstName = data.firstName
        oldData.lastName = data.lastName
        oldData.postalCode = data.postalCode
        oldData.phone = data.phone
        return repository.save(oldData)
    }

    fun getById(id: Long): Customer? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

}