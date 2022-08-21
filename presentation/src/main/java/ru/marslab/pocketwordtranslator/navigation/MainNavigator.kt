package ru.marslab.pocketwordtranslator.navigation

import cafe.adriel.voyager.core.registry.screenModule
import cafe.adriel.voyager.core.screen.Screen
import ru.marslab.pocketwordtranslator.core.BaseScreenProvider
import ru.marslab.pocketwordtranslator.presentation.feature.home.HomeScreen

sealed class NavGraph : BaseScreenProvider {
    object HomeDestination : NavGraph() {
        override val screen: Screen
            get() = HomeScreen()
    }
}

val mainModule = screenModule {
    register<NavGraph.HomeDestination> { it.screen }
}
