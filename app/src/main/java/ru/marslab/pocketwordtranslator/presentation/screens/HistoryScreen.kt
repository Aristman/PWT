package ru.marslab.pocketwordtranslator.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.marslab.pocketwordtranslator.presentation.model.HistoryWordUi
import ru.marslab.pocketwordtranslator.presentation.viewmodels.HistoryViewModel
import ru.marslab.pocketwordtranslator.presentation.views.LCEView

@Composable
fun HistoryScreen(
    historyViewModel: HistoryViewModel,
    onclickItem: (item: HistoryWordUi) -> Unit
) {
    val historyState by historyViewModel.historyList.collectAsState()
    historyViewModel.loadHistory()
    LCEView(appViewState = historyState) { historyList ->
        LazyColumn {
            items(items = historyList) { item ->
                HistoryItem(item, onclickItem)
            }
        }
    }
}

@Composable
fun HistoryItem(item: HistoryWordUi, onclickItem: (item: HistoryWordUi) -> Unit) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(2.dp),
        elevation = 2.dp,
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable { onclickItem(item) }
        ) {
            Text(
                text = item.word,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHistoryItem() {
    HistoryItem(item = HistoryWordUi(1, "Test word")) {}
}