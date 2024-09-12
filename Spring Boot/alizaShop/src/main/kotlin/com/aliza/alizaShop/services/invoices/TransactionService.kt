package com.aliza.alizaShop.services.invoices

import com.aliza.alizaShop.models.invoices.Transaction
import com.aliza.alizaShop.repositories.invoices.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionService {

    @Autowired
    private lateinit var repository: TransactionRepository

    fun insert(data: Transaction): Transaction {
        return repository.save(data)
    }

    fun update(data: Transaction): Transaction? {
        val oldData = getById(data.id) ?: return null
        return repository.save(oldData)
    }

    fun getById(id: Long): Transaction? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getByTransId(transId: String): Transaction? {
        return repository.findByTransId(transId)
    }

}