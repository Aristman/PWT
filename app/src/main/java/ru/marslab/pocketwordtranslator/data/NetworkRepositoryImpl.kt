package ru.marslab.pocketwordtranslator.data

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
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
}