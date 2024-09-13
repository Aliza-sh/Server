package com.aliza.alizaKtor.router

import com.aliza.alizaKtor.data.MockData
import com.aliza.alizaKtor.model.Student
import com.aliza.alizaKtor.service.StudentService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.studentRouting() {

    val service = StudentService(MockData.studentStorage)

    route("/api/student") {

        get {
            call.respond(service.getAll())
        }

        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Please enter id",
                status = HttpStatusCode.BadRequest
            )
            val student = service.getById(id.toInt()) ?: return@get call.respondText(
                "Student not found by id $id",
                status = HttpStatusCode.NotFound
            )
            call.respond(student)
        }

        post {
            val student = call.receive<Student>()
            val isSaved = service.add(student)
            if (isSaved) {
                call.respondText("Student saved", status = HttpStatusCode.Created)
            } else {
                call.respondText("Student has error", status = HttpStatusCode.InternalServerError)
            }
        }

        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respondText(
                "Please enter id",
                status = HttpStatusCode.BadRequest
            )
            call.respond(service.delete(id.toInt()))
        }
    }
}