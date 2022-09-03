package ru.marslab.pocketwordtranslator.presentation.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.marslab.pocketwordtranslator.core.BaseWidgetModel
import ru.marslab.pocketwordtranslator.domain.model.Word
import ru.marslab.pocketwordtranslator.presentation.R
import ru.marslab.pocketwordtranslator.presentation.feature.home.model.HomeAction
import ru.marslab.pocketwordtranslator.presentation.theme.LocalColors
import ru.marslab.pocketwordtranslator.presentation.theme.LocalDimens
import ru.marslab.pocketwordtranslator.presentation.theme.LocalShapes

class WordOfDayCardWidgetModel :
    BaseWidgetModel<WordOfDayCardWidgetModel.WordOfDayState, HomeAction>(
        WordOfDayState()
    ) {
    fun setWord(word: Word) {
        setState {
            WordOfDayState(
                englishWord = word.translates.first(),
                russianWord = word.russianWord
            )
        }
    }

    data class WordOfDayState(
        val englishWord: String = "",
        val russianWord: String = ""
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WordOfDayCard(widgetModel: WordOfDayCardWidgetModel) {
    val state = widgetModel.state.collectAsState()
    val dimens = LocalDimens.current
    val shapes = LocalShapes.current
    Card(
        onClick = { widgetModel sendAction HomeAction.WordOfDayClick(state.value.englishWord) },
        shape = shapes.card,
        modifier = Modifier
            .height(228.dp)
            .fillMaxWidth()
    ) {
        CardBackground()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimens.contentPadding)
        ) {
            CardWordOfDayTitle()
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = state.value.englishWord,
                    fontWeight = FontWeight(600),
                    fontSize = 30.sp,
                    color = LocalColors.current.white80
                )
                Text(
                    text = "-${state.value.russianWord}-",
                    fontWeight = FontWeight(600),
                    fontSize = 18.sp,
                    color = LocalColors.current.white80
                )
            }
            Image(
                painter = painterResource(id = R.drawable.img_check),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
            )
        }
    }
}

@Composable
private fun CardWordOfDayTitle() {
    Text(
        text = stringResource(id = R.string.word_of_day),
        fontWeight = FontWeight(weight = 600),
        fontSize = 18.sp,
        color = LocalColors.current.white50
    )
}

@Composable
private fun CardBackground() {
    Image(
        painter = painterResource(id = R.drawable.img_card_background),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
}
