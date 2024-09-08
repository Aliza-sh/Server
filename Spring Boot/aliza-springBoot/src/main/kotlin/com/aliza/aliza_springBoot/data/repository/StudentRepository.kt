package com.aliza.aliza_springBoot.data.repository

import com.aliza.aliza_springBoot.data.model.Student
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : CrudRepository<Student, String>