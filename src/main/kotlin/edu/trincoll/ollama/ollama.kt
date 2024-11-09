package edu.trincoll.ollama

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

@Serializable
data class OllamaRequest(
    val model: String,
    val prompt: String,
    val Stream: Boolean
)

@Serializable
data class OllamaResponse(
    val model: String,
    val response: String
)

suspend fun main() {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
        engine {
            requestTimeout = 60_000 // Was giving a timeout error without this
        }
    }

    val response = client.post("http://localhost:11434/api/generate") {
        contentType(ContentType.Application.Json)
        accept(ContentType.Application.Json)
        setBody(OllamaRequest("llama3.2", "Explain networking protocols to me", false))
    }

    val jsonParser = Json{ ignoreUnknownKeys = true } //ignore Json keys that are not response or model
    val ollamaResponse = jsonParser.decodeFromString<OllamaResponse>(response.body<String>())

    println(response.status.value)
    println(ollamaResponse.response)
}