package ru.marslab.pocketwordtranslator.presentation.feature.home.model

import ru.marslab.pocketwordtranslator.core.Event

sealed class HomeEvent : Event {
    override fun equals(other: Any?): Boolean = false
    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
