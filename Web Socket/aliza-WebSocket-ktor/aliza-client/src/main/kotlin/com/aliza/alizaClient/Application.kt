package com.aliza.alizaClient

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.runBlocking

fun main() {
    val client = HttpClient(CIO) {
        install(WebSockets) {
            pingInterval = 20_000
        }
    }
    runBlocking {
        client.webSocket(host = "0.0.0.0", port = 8080, path = "/chat") {
            outgoing.send(Frame.Text(readln()))
            while (true) {
                val message = incoming.receive() as Frame.Text
                println(message.readText())
                send(Frame.Text(readln()))
            }
        }
    }
    client.close()
}
