package ru.marslab.pocketwordtranslator.presentation.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import ru.marslab.pocketwordtranslator.presentation.common.EventBaseHandler
import ru.marslab.pocketwordtranslator.presentation.common.KodeinViewModel
import ru.marslab.pocketwordtranslator.presentation.theme.LocalDimens
import ru.marslab.pocketwordtranslator.presentation.widget.AppBottomBar
import ru.marslab.pocketwordtranslator.presentation.widget.HistoryCard
import ru.marslab.pocketwordtranslator.presentation.widget.Logo
import ru.marslab.pocketwordtranslator.presentation.widget.TranslationField
import ru.marslab.pocketwordtranslator.presentation.widget.VSpacerMedium
import ru.marslab.pocketwordtranslator.presentation.widget.WordOfDayCard

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        KodeinViewModel<HomeViewModel> { homeViewModel ->
            MainView(viewModel = homeViewModel)
        }
    }
}

@Composable
private fun MainView(viewModel: HomeViewModel) {
    val state = viewModel.state.collectAsState()
    val event = viewModel.event.collectAsState(null)
    EventBaseHandler(event = event.value) {
        Scaffold(
            backgroundColor = Color.Transparent,
            bottomBar = { AppBottomBar(viewModel.appBottomBarWidgetModel) }
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(LocalDimens.current.contentPadding)
                    .fillMaxSize()
            ) {
                Logo()
                VSpacerMedium()
                TranslationField(viewModel.translationFieldWidgetModel)
                VSpacerMedium()
                WordOfDayCard(viewModel.wordOfDayCardWidgetModel)
                VSpacerMedium()
                HistoryCard(viewModel.historyCardWidgetModel)
            }
        }
    }
}
