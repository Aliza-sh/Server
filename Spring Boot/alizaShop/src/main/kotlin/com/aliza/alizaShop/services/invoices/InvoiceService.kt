package com.aliza.alizaShop.services.invoices

import com.aliza.alizaShop.models.invoices.Invoice
import com.aliza.alizaShop.repositories.invoices.InvoiceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class InvoiceService {

    @Autowired
    private lateinit var repository: InvoiceRepository

    fun insert(data: Invoice, currentUser: String): Invoice {
        return repository.save(data)
    }

    fun update(data: Invoice, currentUser: String): Invoice? {
        val oldData = getById(data.id, currentUser) ?: return null
        oldData.paymentDate = data.paymentDate
        oldData.status = data.status
        return repository.save(oldData)
    }

    fun getById(id: Long, currentUser: String): Invoice? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getAllByUserId(userId: Long, pageIndex: Int, pageSize: Int, currentUser: String): List<Invoice> {
        val pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by("id"))
        return repository.findAllByUserId(userId, pageRequest).toList()
    }

}