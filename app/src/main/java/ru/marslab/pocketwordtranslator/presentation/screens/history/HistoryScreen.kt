package ru.marslab.pocketwordtranslator.presentation.screens.history

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.getViewModel
import ru.marslab.marsbaselibrary.LCEView
import ru.marslab.pocketwordtranslator.presentation.model.HistoryUiState
import ru.marslab.pocketwordtranslator.presentation.views.HistoryItem

@Composable
fun HistoryScreen(onclickItem: (item: HistoryUiState) -> Unit) {
    val historyViewModel = getViewModel<HistoryViewModel>()
    historyViewModel.loadHistory()
    LCEView(appViewState = historyViewModel.uiState) { historyList ->
        LazyColumn {
            items(items = historyList) { item ->
                HistoryItem(item, onclickItem)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHistoryItem() {
    HistoryItem(item = HistoryUiState(1, "Test word")) {}
}
