package com.aliza.alizaKtor.router

import com.aliza.alizaKtor.data.MockData
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.webRouting() {
    route("") {
        get{
            call.respond(FreeMarkerContent("index.ftl", mapOf("data" to MockData.studentStorage), ""))
        }
    }
}