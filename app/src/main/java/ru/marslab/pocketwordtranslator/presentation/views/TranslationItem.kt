package ru.marslab.pocketwordtranslator.presentation.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.marslab.pocketwordtranslator.domain.model.Translate
import ru.marslab.pocketwordtranslator.presentation.model.TranslateWordUi
import ru.marslab.pocketwordtranslator.presentation.theme.Shapes
import ru.marslab.pocketwordtranslator.presentation.theme.Typography

@Composable
fun TranslationItem(item: TranslateWordUi) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(), elevation = 3.dp,
        shape = Shapes.medium,
    ) {
        Column {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = item.word,
                    modifier = Modifier.padding(top = 8.dp),
                    style = Typography.body1
                )
            }
            Divider()
            Text(
                text = item.translation,
                modifier = Modifier.padding(bottom = 8.dp),
                style = Typography.caption
            )
        }
    }
}