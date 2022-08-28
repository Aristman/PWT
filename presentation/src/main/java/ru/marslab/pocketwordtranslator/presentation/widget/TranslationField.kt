package ru.marslab.pocketwordtranslator.presentation.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.marslab.pocketwordtranslator.core.BaseWidgetModel
import ru.marslab.pocketwordtranslator.domain.model.Language
import ru.marslab.pocketwordtranslator.domain.model.switch
import ru.marslab.pocketwordtranslator.presentation.R
import ru.marslab.pocketwordtranslator.presentation.feature.home.model.HomeAction

@Immutable
data class TranslationFieldState(
    val word: String = "",
    val language: Language = Language.Rus
)

class TranslationFieldWidgetModel :
    BaseWidgetModel<TranslationFieldState, HomeAction>(TranslationFieldState()) {

    fun setTranslateWord(word: String) {
        setState { state.value.copy(word = word) }
    }

    fun switchLanguage() {
        setState { state.value.copy(language = state.value.language.switch()) }
    }
}

@Composable
fun TranslationField(widgetModel: TranslationFieldWidgetModel) {
    val widgetState = widgetModel.state.collectAsState()
    Card(
        modifier = Modifier.height(50.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LanguageSwitcher(
                text = widgetState.value.language.name.uppercase(),
                onClick = { widgetModel.sendAction(HomeAction.LanguageClick) }
            )
            WordTextField(widgetState.value.word) {
                widgetModel.setTranslateWord(it)
            }
            TranslateButton() {
                widgetModel.sendAction(HomeAction.TranslateClick(widgetState.value.word))
            }
        }
    }
}

@Composable
private fun TranslateButton(
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(end = 8.dp)
            .size(width = 70.dp, height = 30.dp)
            .clip(RoundedCornerShape(50))
            .clickable { onClick() }
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.base_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Image(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun WordTextField(
    text: String,
    onValueChange: (text: String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        singleLine = true,
        placeholder = { Text(text = stringResource(id = R.string.write_the_word)) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            textColor = MaterialTheme.colors.onPrimary,
            placeholderColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        modifier = Modifier.fillMaxWidth(0.8f)
    )
}

@Composable
private fun LanguageSwitcher(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .clickable { onClick() }
            .padding(start = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight(600)),
            color = MaterialTheme.colors.primary
        )
    }
}
