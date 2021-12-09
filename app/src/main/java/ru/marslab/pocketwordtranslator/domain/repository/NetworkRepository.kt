package ru.marslab.pocketwordtranslator.domain.repository

import io.reactivex.Observable
import okio.BufferedSource
import ru.marslab.pocketwordtranslator.domain.model.Translations

interface NetworkRepository {
    fun getTranslations(word: String): Observable<Translations>
    fun getWordSound(url:String): BufferedSource?
}