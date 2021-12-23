package ru.marslab.pocketwordtranslator.presentation.screens.history

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.getKoin
import org.koin.core.qualifier.named
import ru.marslab.marsbaselibrary.LCEView
import ru.marslab.pocketwordtranslator.di.KoinConstants
import ru.marslab.pocketwordtranslator.presentation.model.HistoryUiState
import ru.marslab.pocketwordtranslator.presentation.views.HistoryItem

@Composable
fun HistoryScreen(onclickItem: (item: HistoryUiState) -> Unit) {
    val historyScope =
        getKoin().getOrCreateScope(
            KoinConstants.HISTORY_SCOPE,
            named(KoinConstants.HISTORY_SCOPE)
        )
    val historyViewModel: HistoryViewModel by historyScope.inject()
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
