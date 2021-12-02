package ru.marslab.pocketwordtranslator.presentation.model

sealed class AppAction {
    object Show: AppAction()
    object Hide: AppAction()
}
