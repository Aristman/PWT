package ru.marslab.pocketwordtranslator.presentation.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import ru.marslab.pocketwordtranslator.presentation.core.MakeViewModel
import ru.marslab.pocketwordtranslator.presentation.feature.home.model.HomeAction

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        MakeViewModel<HomeViewModel> { homeViewModel ->
            MainView(viewModel = homeViewModel)
        }
    }
}

@Composable
private fun MainView(viewModel: HomeViewModel) {
    val state = viewModel.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = { viewModel.sendAction(HomeAction.TestClick) }) {
            Text(text = state.value.word)
        }
    }
}
