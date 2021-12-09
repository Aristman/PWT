package ru.marslab.pocketwordtranslator.data.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object PwtRetrofit {
    private const val TRANSLATE_BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"

    fun createPwtApi(retrofit: Retrofit): PwtApi =
        retrofit.create(PwtApi::class.java)

    fun createRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(TRANSLATE_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
}
