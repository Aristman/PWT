package com.example.poketwordtranslator.presentation.view

import com.example.poketwordtranslator.presentation.model.AppViewState

interface MainView : BaseView {
    fun handlingData(viewState: AppViewState)
}

