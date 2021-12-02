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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.marslab.pocketwordtranslator.R
import ru.marslab.pocketwordtranslator.presentation.model.AppAction
import ru.marslab.pocketwordtranslator.presentation.model.AppViewState
import ru.marslab.pocketwordtranslator.presentation.model.TranslateWordUi
import ru.marslab.pocketwordtranslator.presentation.viewmodels.AppActionViewModel
import ru.marslab.pocketwordtranslator.presentation.viewmodels.TranslationViewModel
import ru.marslab.pocketwordtranslator.presentation.views.SearchWordDialog
import ru.marslab.pocketwordtranslator.presentation.views.TranslationItem

@Composable
fun WordTranslationScreen(viewModel: TranslationViewModel) {
    val viewState = viewModel.translationsState.collectAsState()
    val translationsState = viewState.value

    val searchViewModel = remember {
        AppActionViewModel()
    }
    val searchDialogAction = searchViewModel.appAction.collectAsState()
    if (searchDialogAction.value is AppAction.Show) {
        SearchWordDialog(viewModel = searchViewModel) { word ->
            viewModel.getTranslations(word)
        }
    }
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
        when (translationsState) {
            is AppViewState.Success<*> -> {
                val result =
                    (translationsState.data as List<*>).map { it as TranslateWordUi }
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
            onClick = { searchViewModel.viewShow() },
            modifier = Modifier.padding(bottom = 24.dp, end = 24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_search_24),
                contentDescription = null
            )
        }
    }
}
