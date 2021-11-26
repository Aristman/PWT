package com.example.poketwordtranslator.domain.repository

import io.reactivex.Observable

interface Repository<T> {
    fun getData(word: String): Observable<T>
}