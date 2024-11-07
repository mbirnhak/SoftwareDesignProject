package edu.trincoll.ollama

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

@Serializable
data class OllamaRequest(
    val model: String,
    val prompt: String,
    val Stream: Boolean
)

@Serializable
data class OllamaResponse(
    val model: String,
    val responses: String
)

suspend fun main() {
    val client = HttpClient(CIO)

    val response = client.post("http://localhost:11434/api/generate") {
        contentType(ContentType.Application.Json)
        setBody(OllamaRequest("llama3.2", "Why is the sky blue?", false))
    }

    println(response.status.value)
    println(response.body<String>())
}