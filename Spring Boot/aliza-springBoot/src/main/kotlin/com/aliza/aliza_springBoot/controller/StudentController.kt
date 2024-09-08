package com.aliza.aliza_springBoot.controller

import com.aliza.aliza_springBoot.data.model.Student
import com.aliza.aliza_springBoot.data.repository.StudentRepository
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class StudentController {

    lateinit var _studentRepository: StudentRepository
    @Autowired
    fun set_student_repository(studentRepository: StudentRepository) {
        _studentRepository = studentRepository
    }

    @GetMapping("/student")
    fun getAllStudents(): ResponseEntity<MutableIterable<Student>> {
        val dataFromDatabase = _studentRepository.findAll()
        return ResponseEntity.ok(dataFromDatabase)
    }

    @PostMapping("/student")
    fun insertStudent(@RequestBody data: String): ResponseEntity<Int> {

        val gson = Gson()
        val newStudent = gson.fromJson(data, Student::class.java)

        _studentRepository.save(newStudent)

        //Here you can check the data whether what we want is.
        return ResponseEntity.ok(200)
    }

    @PutMapping("/student/updating{name}")
    fun updateStudent(
        @PathVariable("name") name: String,
        @RequestBody data: String
    ): ResponseEntity<Int> {

        val gson = Gson()
        val newStudent: Student = gson.fromJson(data, Student::class.java)

        _studentRepository.save(newStudent)

        println(name)

        //Here you can check the data whether what we want is.
        return ResponseEntity.ok(200)
    }

    @DeleteMapping("/student/deleting{name}")
    fun deleteStudent( @PathVariable("name") name:String ) : ResponseEntity<Int> {

        _studentRepository.deleteById(name)
        return ResponseEntity.ok(200)

    }

}
