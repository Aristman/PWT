package ru.marslab.pocketwordtranslator.presentation.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import ru.marslab.pocketwordtranslator.presentation.core.MakeViewModel
import ru.marslab.pocketwordtranslator.presentation.widget.Logo
import ru.marslab.pocketwordtranslator.presentation.widget.TranslationField
import ru.marslab.pocketwordtranslator.presentation.widget.VSpacerMedium

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
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {
        Logo()
        VSpacerMedium()
        TranslationField(viewModel.translationFieldWidgetModel)
    }
}
