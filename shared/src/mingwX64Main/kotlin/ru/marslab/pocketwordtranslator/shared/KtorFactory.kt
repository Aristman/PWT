package ru.marslab.pocketwordtranslator.shared

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

internal actual class KtorFactory {
    actual fun getClient(
        enableLogging: Boolean,
        config: HttpClientConfig<*>.() -> Unit
    ): HttpClient {
        TODO("Not yet implemented")
    }
}
