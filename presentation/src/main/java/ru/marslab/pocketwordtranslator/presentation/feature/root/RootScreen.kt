package ru.marslab.pocketwordtranslator.presentation.feature.root // ktlint-disable filename

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.Navigator
import org.kodein.di.compose.localDI
import org.kodein.di.instance
import ru.marslab.pocketwordtranslator.presentation.navigation.NavGraph
import ru.marslab.pocketwordtranslator.presentation.theme.appBackground
import ru.marslab.pocketwordtranslator.presentation.theme.primaryLightColor
import ru.marslab.pocketwordtranslator.presentation.widget.AppBottomBar

@Composable
fun RootView() {
    val viewModel: RootViewModel by localDI().instance()
    Scaffold(
        backgroundColor = primaryLightColor,
        bottomBar = { AppBottomBar(viewModel.appBottomBarWidgetModel) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .appBackground()
        ) {
            Navigator(screen = rememberScreen(provider = NavGraph.HomeDestination))
        }
    }
}
