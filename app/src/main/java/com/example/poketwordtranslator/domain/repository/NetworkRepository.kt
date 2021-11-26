package com.example.poketwordtranslator.domain.repository

import com.example.poketwordtranslator.domain.model.Translations
import io.reactivex.Observable

interface NetworkRepository {
    fun getTranslations(word: String): Observable<Translations>
}