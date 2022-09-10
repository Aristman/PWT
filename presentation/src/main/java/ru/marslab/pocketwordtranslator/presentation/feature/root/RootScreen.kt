package ru.marslab.pocketwordtranslator.presentation.feature.root // ktlint-disable filename

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorContent
import ru.marslab.pocketwordtranslator.presentation.common.KodeinViewModel
import ru.marslab.pocketwordtranslator.presentation.navigation.NavGraph
import ru.marslab.pocketwordtranslator.presentation.theme.appBackground
import ru.marslab.pocketwordtranslator.presentation.theme.primaryLightColor
import ru.marslab.pocketwordtranslator.presentation.widget.AppBottomBar
import ru.marslab.pocketwordtranslator.presentation.widget.MainFloatingActionButton

class RootScreen : Screen {

    @Composable
    override fun Content() {
        KodeinViewModel<RootViewModel> { viewModel ->
            RootView(viewModel = viewModel)
        }
    }
}

@Composable
fun RootView(
    viewModel: RootViewModel,
    content: NavigatorContent = { CurrentScreen() }
) {
    Navigator(screen = rememberScreen(provider = NavGraph.HomeDestination)) { navigator ->
        viewModel.setNavigator(navigator)
        Scaffold(
            backgroundColor = primaryLightColor,
            bottomBar = { AppBottomBar(viewModel.appBottomBarWidgetModel) },
            floatingActionButton = { MainFloatingActionButton(viewModel.mainFABWidgetModel) },
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = true
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .appBackground()
            ) {
                content(navigator)
            }
        }
    }
}
