package com.aliza.alizaKtor.plugins

import com.aliza.alizaKtor.router.studentRouting
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        staticResources("/src","static")
        studentRouting()
    }
}
