package com.aliza.alizaKtor.plugins

import freemarker.cache.*
import com.aliza.alizaKtor.router.webRouting
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.routing.*

fun Application.configureTemplating() {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }
    routing {
        webRouting()
    }
}

data class IndexData(val items: List<Int>)
