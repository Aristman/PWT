package ru.marslab.pocketwordtranslator.presentation.feature.home.model

import ru.marslab.pocketwordtranslator.core.Action

sealed class HomeAction : Action {
    object TestClick : HomeAction()

    override fun equals(other: Any?): Boolean = false
    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
