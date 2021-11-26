package com.example.poketwordtranslator.domain.presenter

import com.example.poketwordtranslator.presentation.View
import com.example.poketwordtranslator.presentation.model.ViewState

interface Presenter<T : ViewState, V : View> {
    fun attachView(view: V)
    fun detachView(view: V)
    fun getData(word: String, isOnline: Boolean)
}