package ru.marslab.pocketwordtranslator.presentation.common.model // ktlint-disable filename

import ru.marslab.pocketwordtranslator.core.Action

sealed class BottomNavigationAction : Action {
    object HomeScreen : BottomNavigationAction()
    object CardsScreen : BottomNavigationAction()
    object SettingsScreen : BottomNavigationAction()
}
