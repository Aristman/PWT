package ru.marslab.pocketwordtranslator.core

import cafe.adriel.voyager.core.registry.ScreenProvider
import cafe.adriel.voyager.core.screen.Screen

interface BaseScreenProvider : ScreenProvider {
    val screen: Screen
}
