package domain.networking

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*

object KtorClient {
    private val client = HttpClient{
        install(ContentNegotiation){
            json()
        }
    }

    suspend fun test(): String {
       return client.get("https://jsonplaceholder.typicode.com/todos/1").body<String>()
    }
}