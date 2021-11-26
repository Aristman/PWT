package com.example.poketwordtranslator.domain.model

import io.reactivex.Observable

interface DataSource<T> {
    fun getData(word: String): Observable<T>
}