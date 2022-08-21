package ru.marslab.pocketwordtranslator.presentation.feature.home.model

import ru.marslab.pocketwordtranslator.core.Action

sealed class HomeAction : Action {
    object TestClick : HomeAction()
}
