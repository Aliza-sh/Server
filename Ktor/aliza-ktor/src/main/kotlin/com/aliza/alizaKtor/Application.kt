package com.aliza.alizaKtor

import com.aliza.alizaKtor.plugins.configureHTTP
import com.aliza.alizaKtor.plugins.configureRouting
import com.aliza.alizaKtor.plugins.configureSerialization
import com.aliza.alizaKtor.plugins.configureTemplating
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureTemplating()
    configureSerialization()
    configureHTTP()
    configureRouting()
}
