package ru.marslab.pocketwordtranslator.presentation.view

import ru.marslab.pocketwordtranslator.presentation.model.AppViewState

interface MainView : BaseView {
    fun handlingData(viewState: AppViewState)
}

