package ru.marslab.pocketwordtranslator.data

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.Call
import okio.BufferedSource
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.inject
import ru.marslab.pocketwordtranslator.data.retrofit.PwtApi
import ru.marslab.pocketwordtranslator.domain.model.Translations
import ru.marslab.pocketwordtranslator.domain.repository.NetworkRepository


class NetworkRepositoryImpl(private val translateService: PwtApi) : NetworkRepository {

    override fun getTranslations(word: String): Observable<Translations> {
        return translateService.searchWord(word)
            .flattenAsObservable { list ->
                list.map { it.toDomain() }
            }
            .subscribeOn(Schedulers.io())
    }

    @Throws(Exception::class)
    override fun getWordSound(url: String): BufferedSource? {
        val httpCall: Call by inject(Call::class.java) { parametersOf(url) }
        val response = httpCall.execute()
        return if (response.isSuccessful && response.body != null) {
            response.body?.source()
        } else {
            null
        }
    }
}