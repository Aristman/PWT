package ru.marslab.pocketwordtranslator.domain.interactor

import io.reactivex.Observable
import okhttp3.Response
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.inject
import ru.marslab.pocketwordtranslator.domain.model.Translations
import ru.marslab.pocketwordtranslator.domain.repository.NetworkRepository

class TranslationInteractorImpl(
    private val networkRepository: NetworkRepository
) : TranslationInteractor {
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<Translations> =
        networkRepository.getTranslations(word)
}