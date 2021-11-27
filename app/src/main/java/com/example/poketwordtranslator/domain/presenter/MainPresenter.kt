package com.example.poketwordtranslator.domain.presenter

import com.example.poketwordtranslator.presentation.view.MainView

interface MainPresenter {
        fun attachView(view: MainView)
        fun detachView(view: MainView)
        fun getData(word: String, isOnline: Boolean)
}

