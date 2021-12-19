package ru.marslab.pocketwordtranslator.data.okhttp

import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

object PwtOkHttp {
    private const val REQUEST_BASE_SCHEME = "https:"

    fun createClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    fun getCall(client: OkHttpClient, url: String): Call =
        client.newCall(
            Request.Builder()
                .url(REQUEST_BASE_SCHEME + url)
                .build()
        )
}
