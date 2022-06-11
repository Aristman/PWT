package ru.marslab.pocketwordtranslator.shared

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.util.concurrent.TimeUnit

internal actual class KtorFactory {
    actual fun getClient(
        enableLogging: Boolean,
        config: HttpClientConfig<*>.() -> Unit
    ): HttpClient =
        HttpClient(OkHttp) {
            config(this)
            if (enableLogging) {
                install(Logging) {
                    logger = LoggerImpl
                    level = LogLevel.ALL
                }
            }
            engine {
                config {
                    retryOnConnectionFailure(true)
                    connectTimeout(5, TimeUnit.SECONDS)
                }
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        coerceInputValues = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }

    operator fun invoke(enableLogging: Boolean): HttpClient =
        getClient(enableLogging) {
        }
}

private object LoggerImpl : Logger {
    private const val TAG = "HttpLogger"

    override fun log(message: String) {
        Log.i(TAG, message)
    }
}
