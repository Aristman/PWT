package com.example.poketwordtranslator.data

import com.example.poketwordtranslator.domain.model.Translations
import com.example.poketwordtranslator.domain.repository.NetworkRepository
import io.reactivex.Observable

class NetworkRepositoryImpl : NetworkRepository {

    override fun getTranslations(word: String): Observable<Translations> {
        TODO("Not yet implemented")
    }
}