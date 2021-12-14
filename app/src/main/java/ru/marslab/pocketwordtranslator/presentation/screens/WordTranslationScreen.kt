package ru.marslab.pocketwordtranslator.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.marslab.pocketwordtranslator.R
import ru.marslab.pocketwordtranslator.presentation.viewmodels.SoundViewModel
import ru.marslab.pocketwordtranslator.presentation.viewmodels.TranslationViewModel
import ru.marslab.pocketwordtranslator.presentation.views.LCEView
import ru.marslab.pocketwordtranslator.presentation.views.SearchWordDialog
import ru.marslab.pocketwordtranslator.presentation.views.TranslationItem
import ru.marslab.pocketwordtranslator.presentation.views.WordSoundDialog

@Composable
fun WordTranslationScreen(
    translationViewModel: TranslationViewModel,
    soundViewModel: SoundViewModel
) {
    val translationsState by translationViewModel.translationsState.collectAsState()

    val (isVisibleSearchDialog, setVisibleSearchDialog) = remember { mutableStateOf(false) }
    if (isVisibleSearchDialog) {
        SearchWordDialog(setVisibleSearchDialog) { word ->
            translationViewModel.getTranslations(word)
        }
    }

    val soundState by soundViewModel.soundState.collectAsState()
    val (isVisibleSoundView, setVisibleSoundView) = remember { mutableStateOf(false) }
    if (isVisibleSoundView) {
        WordSoundDialog(
            soundState,
            setVisibleSoundView
        )
    }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
        LCEView(appViewState = translationsState) { data ->
            Box {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    items(items = data) {
                        var isExpanded by rememberSaveable { mutableStateOf(false) }
                        TranslationItem(
                            item = it,
                            isExpanded = isExpanded,
                            onClickItem = { isExpanded = !isExpanded }
                        ) { url ->
                            soundViewModel.getWordSound(url, it.word)
                            setVisibleSoundView(true)
                        }
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = { setVisibleSearchDialog(true) },
            modifier = Modifier.padding(bottom = 24.dp, end = 24.dp),
            backgroundColor = MaterialTheme.colors.secondaryVariant
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_search_24),
                contentDescription = null
            )
        }
    }
}
