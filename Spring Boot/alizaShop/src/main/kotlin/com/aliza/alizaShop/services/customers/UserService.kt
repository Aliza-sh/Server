package com.aliza.alizaShop.services.customers

import com.aliza.alizaShop.models.customers.User
import com.aliza.alizaShop.repositories.customers.UserRepository
import com.aliza.alizaShop.utils.SecurityUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    lateinit var repository: UserRepository

    @Autowired
    lateinit var customerService: CustomerService

    fun insert(data: User): User {
        if (data.username.isEmpty())
            throw Exception("Please enter username")
        if (data.password.isEmpty())
            throw Exception("Please enter password")

        val oldData = getByUsername(data.username)
        if (oldData != null)
            throw Exception("Already registered with this username")

        val password = SecurityUtil.encryptSHA256(data.password)
        data.password = password

        customerService.insert(data.customer!!)
        val savedData = repository.save(data)
        savedData.password = ""
        return savedData
    }

    fun getByUsername(user: String): User? {
        if (user.isEmpty())
            throw Exception("Please fill username")
        return repository.findFirstByUsername(user)
    }

    fun update(data: User, currentUser: String): User? {
        val user = repository.findFirstByUsername(currentUser)
        if (user == null || user.id != data.id)
            throw Exception("You don't have permission to update info")
        val oldCustomer = customerService.getById(data.customer!!.id) ?: return null
        if (data.customer!!.postalCode.isNotEmpty())
            oldCustomer.postalCode = data.customer!!.postalCode
        if (data.customer!!.phone.isNotEmpty())
            oldCustomer.phone = data.customer!!.phone
        if (data.customer!!.lastName.isNotEmpty())
            oldCustomer.lastName = data.customer!!.lastName
        if (data.customer!!.firstName.isNotEmpty())
            oldCustomer.firstName = data.customer!!.firstName
        if (data.customer!!.address.isNotEmpty())
            oldCustomer.address = data.customer!!.address
        customerService.update(oldCustomer)
        data.password = ""
        return data
    }

    fun getById(id: Long): User? {
        val data = repository.findById(id)
        if (data.isEmpty) return null
        return data.get()
    }

    fun getUserByUserAndPas(user: String, pass: String): User? {
        if (user.isEmpty() || pass.isEmpty())
            throw Exception("Please fill username and password")
        val password = SecurityUtil.encryptSHA256(pass)
        return repository.findFirstByUsernameAndPassword(user, password)
    }

    fun changePassword(userName: String,currentPass: String, oldPassword: String, repeatPassword: String, currentUser: String): User? {
        val userPass = repository.findFirstByUsername(userName)
        val userToken = repository.findFirstByUsername(currentUser)
        if (userToken == null || userToken.id != userPass?.id)
            throw Exception("You don't have permission to update info")
        if (currentPass != repeatPassword)
            throw Exception("Password not matched to repeat password")
        //TODO: check password strength
        if (userToken.password != SecurityUtil.encryptSHA256(oldPassword))
            throw Exception("Invalid current password")
        userToken.password = SecurityUtil.encryptSHA256(userPass!!.password)
        val savedData = repository.save(userToken)
        savedData.password = ""
        return savedData
    }

}