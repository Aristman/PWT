package ru.marslab.pocketwordtranslator.data

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.marslab.pocketwordtranslator.data.retrofit.PwtApi
import ru.marslab.pocketwordtranslator.domain.model.Translations
import ru.marslab.pocketwordtranslator.domain.repository.NetworkRepository

private const val TRANSLATE_BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"

class NetworkRepositoryImpl : NetworkRepository {

    private val client: OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(TRANSLATE_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private val translateService = retrofit.create(PwtApi::class.java)

    override fun getTranslations(word: String): Observable<Translations> {
        return translateService.searchWord(word)
            .flattenAsObservable { list ->
                list.map { it.toDomain() }
            }
            .subscribeOn(Schedulers.io())
    }
}