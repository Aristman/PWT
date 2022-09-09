package ru.marslab.pocketwordtranslator.presentation.navigation

import cafe.adriel.voyager.core.registry.screenModule
import cafe.adriel.voyager.core.screen.Screen
import ru.marslab.pocketwordtranslator.core.BaseScreenProvider
import ru.marslab.pocketwordtranslator.presentation.feature.home.HomeScreen
import ru.marslab.pocketwordtranslator.presentation.feature.root.RootScreen

sealed class NavGraph : BaseScreenProvider {
    object HomeDestination : NavGraph() {
        override val screen: Screen
            get() = HomeScreen()
    }

    object RootDestination : NavGraph() {
        override val screen: Screen
            get() = RootScreen()
    }
}

val mainModule = screenModule {
    register<NavGraph.HomeDestination> { it.screen }
    register<NavGraph.RootDestination> { it.screen }
}
