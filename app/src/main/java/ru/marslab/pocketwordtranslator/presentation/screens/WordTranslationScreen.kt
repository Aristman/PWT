package ru.marslab.pocketwordtranslator.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.marslab.pocketwordtranslator.R
import ru.marslab.pocketwordtranslator.domain.model.Translate
import ru.marslab.pocketwordtranslator.domain.model.Translations
import ru.marslab.pocketwordtranslator.presentation.model.AppViewState
import ru.marslab.pocketwordtranslator.presentation.model.TranslateWordUi
import ru.marslab.pocketwordtranslator.presentation.viewmodels.TranslationViewModel
import ru.marslab.pocketwordtranslator.presentation.views.TranslationItem
import java.lang.Thread.sleep
import kotlin.concurrent.thread


@Composable
fun WordTranslationScreen(viewModel: TranslationViewModel) {
    val translations by viewModel.translations.collectAsState()
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
        when (translations) {
            is AppViewState.Success<*> -> {
                val result =
                    ((translations as AppViewState.Success<*>).data as List<*>).map { it as TranslateWordUi }
                Box {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        items(items = result) {
                            TranslationItem(item = it)
                        }
                    }
                }
            }
            is AppViewState.Error -> {
                Toast.makeText(
                    LocalContext.current,
                    stringResource(id = R.string.error_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
            AppViewState.Init -> {

            }
            is AppViewState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
        FloatingActionButton(
            onClick = { viewModel.getTranslations("test") },
            modifier = Modifier.padding(bottom = 24.dp, end = 24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_search_24),
                contentDescription = null
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TranslationsPreView() {
    val viewModel = TranslationViewModelMoca()
    WordTranslationScreen(viewModel = viewModel)
    thread {
        viewModel.setLoading()
        sleep(3000L)
        viewModel.setError()
        sleep(3000L)
        viewModel.getTranslations("test")
    }
}

private class TranslationViewModelMoca : TranslationViewModel {
    private val _translations = MutableStateFlow<AppViewState>(AppViewState.Init)
    override val translations: StateFlow<AppViewState> =
        _translations.asStateFlow()

    fun setLoading() {
        _translations.tryEmit(AppViewState.Loading(null))
    }

    fun setError() {
        _translations.tryEmit(AppViewState.Error(Throwable()))
    }

    override fun getTranslations(word: String) {
        _translations.tryEmit(AppViewState.Success(Translations(
            1,
            word,
            (20..40).map {
                Translate(
                    it,
                    "image $it",
                    "sound $it",
                    "transcription $it$it",
                    "Translation $it-${it / 2}",
                    "Note $it Note"
                )
            }
        )))
    }
}