package ru.marslab.shared.domain.repository

import io.reactivex.Observable
import okio.BufferedSource
import ru.marslab.shared.domain.model.Translations

interface NetworkRepository {
    fun getTranslations(word: String): Observable<Translations>
    fun getWordSound(url: String): BufferedSource?
}
