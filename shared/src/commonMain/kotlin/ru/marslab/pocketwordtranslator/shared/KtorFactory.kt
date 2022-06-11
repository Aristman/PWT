package ru.marslab.pocketwordtranslator.shared

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

internal expect class KtorFactory() {
    fun getClient(
        enableLogging: Boolean,
        config: HttpClientConfig<*>.() -> Unit
    ): HttpClient
}
