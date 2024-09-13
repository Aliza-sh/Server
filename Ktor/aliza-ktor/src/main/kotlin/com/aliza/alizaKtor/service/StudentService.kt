package com.aliza.alizaKtor.service

import com.aliza.alizaKtor.model.Student

class StudentService(private val data: MutableList<Student>) {

    fun getAll(): List<Student> {
        return data
    }

    fun getById(id: Int): Student? {
        return data.find { x -> x.id == id }
    }

    fun delete(id: Int): Boolean {
        return data.removeIf { x -> x.id == id }
    }

    fun add(student: Student): Boolean {
        if (student.firstName == null || student.firstName.isEmpty()) {
            throw Exception("Please enter firstName")
        }
        if (student.lastName == null || student.lastName.isEmpty()) {
            throw Exception("Please enter lastName")
        }
        if (student.code == null || student.code.isEmpty()) {
            throw Exception("Please enter code")
        }
        return data.add(student)
    }
}