package ru.marslab.pocketwordtranslator.presentation.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.marslab.pocketwordtranslator.presentation.model.TranslateWordUi
import ru.marslab.pocketwordtranslator.presentation.theme.Shapes
import ru.marslab.pocketwordtranslator.presentation.theme.Typography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TranslationItem(
    item: TranslateWordUi,
    isExpanded: Boolean,
    onClickItem: (TranslateWordUi) -> Unit,
    onClickSound: (url: String) -> Unit
) {
    Card(
        modifier = Modifier
            .combinedClickable(
                onClick = { onClickItem(item) },
                onLongClick = {onClickSound(item.sound.first())}
            )
            .padding(4.dp)
            .fillMaxWidth(),
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
            val items = if (isExpanded) {
                item.translation
            } else {
                listOf(item.translation.first())
            }
            items.forEach { translation ->
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = CenterVertically
                ) {
                    Text(
                        text = translation,
                        modifier = Modifier
                            .padding(start = 12.dp, bottom = 4.dp, top = 4.dp),
                        style = Typography.body1
                    )
                }
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
            word = "Test",
            translation = listOf("пример перевода Слова", "пример перевода Слова"),
            sound = listOf("1,", "2")
        ),
        isExpanded = isExpanded,
        onClickItem = { isExpanded = !isExpanded },
        onClickSound = {}
    )
}