package ru.marslab.pocketwordtranslator.domain.interactor

import io.reactivex.Observable

interface Interactor<T> {

    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
}