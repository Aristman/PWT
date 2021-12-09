package ru.marslab.pocketwordtranslator.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.marslab.pocketwordtranslator.data.okhttp.PwtOkHttp
import ru.marslab.pocketwordtranslator.data.retrofit.PwtApi
import ru.marslab.pocketwordtranslator.data.retrofit.PwtRetrofit
import javax.inject.Singleton

private const val TRANSLATE_BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        PwtRetrofit.createRetrofit(client)

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        PwtOkHttp.createClient()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): PwtApi =
        PwtRetrofit.createPwtApi(retrofit)
}