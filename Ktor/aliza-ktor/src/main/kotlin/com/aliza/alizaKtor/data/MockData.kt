package com.aliza.alizaKtor.data

import com.aliza.alizaKtor.model.Student

class MockData {
    companion object {
        val studentStorage = mutableListOf<Student>()

        init {
            studentStorage.add(Student(1, "aliza", "Shah", "1234"))
            studentStorage.add(Student(2, "Alireza", "shahsavari", "5588"))
            studentStorage.add(Student(3, "ALiza", "shahsavari", "5577"))
        }
    }
}