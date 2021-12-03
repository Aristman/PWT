package ru.marslab.pocketwordtranslator.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.marslab.pocketwordtranslator.presentation.model.TranslateWordUi
import ru.marslab.pocketwordtranslator.presentation.theme.Shapes
import ru.marslab.pocketwordtranslator.presentation.theme.Typography

@Composable
fun TranslationItem(item: TranslateWordUi, onClickItem: (TranslateWordUi) -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable { onClickItem(item) },
        elevation = 3.dp,
        shape = Shapes.medium,
    ) {
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.secondary),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = item.word,
                    modifier = Modifier.padding(top = 8.dp),
                    style = Typography.h5
                )
            }
            Divider()
            val items = if (item.isExpanded) {
                item.translation
            } else {
                item.translation.subList(0, 1)
            }
            items.forEach {
                Text(
                    text = it,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .padding(start = 12.dp),
                    style = Typography.body1
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ItemPreView() {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    TranslationItem(
        item = TranslateWordUi(
            "Test",
            listOf("пример перевода Слова", "пример перевода Слова"),
            emptyList(),
            isExpanded
        )
    ) { isExpanded = !isExpanded }
}