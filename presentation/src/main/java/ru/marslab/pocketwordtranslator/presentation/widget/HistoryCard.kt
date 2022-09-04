package ru.marslab.pocketwordtranslator.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.marslab.pocketwordtranslator.core.BaseWidgetModel
import ru.marslab.pocketwordtranslator.domain.model.Language
import ru.marslab.pocketwordtranslator.domain.model.Word
import ru.marslab.pocketwordtranslator.presentation.R
import ru.marslab.pocketwordtranslator.presentation.feature.home.model.HomeAction
import ru.marslab.pocketwordtranslator.presentation.theme.GetLocalProperties
import ru.marslab.pocketwordtranslator.presentation.theme.PwtColors

class HistoryCardWidgetModel() :
    BaseWidgetModel<HistoryCardWidgetModel.HistoryCardState, HomeAction>(HistoryCardState()) {

    fun setHistory(words: List<Word>) {
        setState {
            state.value.copy(
                words = words.map {
                    HistoryWord(
                        englishWord = it.translates.first(),
                        russianWord = it.russianWord
                    )
                }
            )
        }
    }

    @Immutable
    data class HistoryCardState(
        val words: List<HistoryWord> = emptyList()
    )

    @Immutable
    data class HistoryWord(
        val englishWord: String,
        val russianWord: String
    )
}

@Composable
fun HistoryCard(widgetModel: HistoryCardWidgetModel) {
    val state = widgetModel.state.collectAsState()
    GetLocalProperties { dimens, _, colors, shapes ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(shapes.card)
                .background(colors.white30)
                .padding(dimens.contentPadding)
        ) {
            HistoryHeader()
            HistoryList(words = state.value.words, colors = colors) { word, language ->
                widgetModel.sendAction(HomeAction.HistoryWordClick(word, language))
            }
        }
    }
}

@Composable
private fun HistoryList(
    words: List<HistoryCardWidgetModel.HistoryWord>,
    colors: PwtColors,
    onClick: (word: String, language: Language) -> Unit
) {
    LazyColumn {
        items(items = words) { word ->
            Row {
                Box(
                    modifier = Modifier
                        .height(32.dp)
                        .fillMaxWidth(0.5f)
                        .background(colors.gray)
                        .clickable { onClick(word.englishWord, Language.Eng) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = word.englishWord,
                        textAlign = TextAlign.Center,
                        color = colors.darkGray70
                    )
                }
                Box(
                    modifier = Modifier
                        .height(32.dp)
                        .fillMaxWidth()
                        .background(colors.lightGray)
                        .clickable { onClick(word.russianWord, Language.Rus) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = word.russianWord,
                        textAlign = TextAlign.Center,
                        color = colors.darkGray50
                    )
                }
            }
            VSpacerSmall()
        }
    }
}

@Composable
private fun HistoryHeader() {
    Text(
        text = stringResource(id = R.string.history),
        fontSize = 18.sp
    )
    VSpacer(height = 16)
}
