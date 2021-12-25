package ru.marslab.pocketwordtranslator.presentation.screens.history

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
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
                HistoryItem(
                    item = item,
                    onclickItem = onclickItem,
                    onDeleteItem = {
                        historyViewModel.deleteWord(it)
                        historyViewModel.loadHistory()
                    }
                )
            }
        }
    }
}
