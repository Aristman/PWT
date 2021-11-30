package ru.marslab.pocketwordtranslator.domain.presenter

import ru.marslab.pocketwordtranslator.presentation.view.MainView

interface MainPresenter {
        fun attachView(view: MainView)
        fun detachView(view: MainView)
        fun getData(word: String, isOnline: Boolean)
}
