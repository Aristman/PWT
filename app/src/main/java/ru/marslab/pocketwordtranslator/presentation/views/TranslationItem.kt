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
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
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
                onLongClick = { onClickSound(item.sound) }
            )
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = 3.dp,
        shape = Shapes.medium,
    ) {
        if (isExpanded) {
            ExpandItem(item)
        } else {
            CollapseItem(item)
        }
    }
}

@Composable
private fun ExpandItem(
    item: TranslateWordUi
) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier.fillMaxWidth()
        ) {
            GlideImage(
                modifier = Modifier.size(80.dp),
                imageModel = item.image,
                contentScale = ContentScale.Crop,
                circularReveal = CircularReveal(500),
                loading = { CircularProgressIndicator(modifier = Modifier.align(Center)) }
            )
            Column(
                Modifier
                    .fillMaxWidth(1f)
                    .background(MaterialTheme.colors.secondary),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = item.word,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = Typography.h5
                )
                Divider()
                Text(
                    text = item.transcription,
                    modifier = Modifier.padding(top = 8.dp),
                    style = Typography.h6
                )
            }
        }
        item.translation.forEach { translation ->
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

@Composable
private fun CollapseItem(
    item: TranslateWordUi
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
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = CenterVertically
        ) {
            Text(
                text = item.translation.first(),
                modifier = Modifier
                    .padding(start = 12.dp, bottom = 4.dp, top = 4.dp),
                style = Typography.body1
            )
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
        item = mocaItem,
        isExpanded = isExpanded,
        onClickItem = { isExpanded = !isExpanded },
        onClickSound = {}
    )
}

private val mocaItem = TranslateWordUi(
    id = 1,
    word = "Test",
    translation = listOf("пример перевода Слова", "пример перевода Слова"),
    sound = "1",
    image = "https://cdn.fishki.net/upload/post/2019/05/15/2978629/a1ce870931cec5e01536340c798309e0.jpg",
    transcription = "transcription",
    preview = "https://cdn.fishki.net/upload/post/2019/05/15/2978629/a1ce870931cec5e01536340c798309e0.jpg"
)