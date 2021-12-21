package ru.marslab.pocketwordtranslator.presentation.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.marslab.pocketwordtranslator.presentation.model.AppState

private const val LOADING_ERROR = "Ошибка загрузки!"
private const val REPEAT = "Повторить"

@Composable
fun <D, E> LCEView(
    appViewState: AppState<D, E>,
    initContent: (@Composable () -> Unit)? = null,
    loadingContent: (@Composable () -> Unit)? = null,
    errorContent: (@Composable (e: E) -> Unit)? = null,
    repeatLoading: (() -> Unit)? = null,
    mainContent: @Composable (data: D) -> Unit
) {
    when (appViewState) {
        AppState.Init -> {
            initContent?.let { it() }
        }
        is AppState.Error -> {
            if (errorContent == null) {
                DefaultErrorView(repeatLoading)
            } else {
                errorContent(appViewState.error)
            }
        }
        is AppState.Loading -> {
            if (loadingContent == null) {
                DefaultLoadingContent()
            } else {
                loadingContent()
            }
        }
        is AppState.Success<D> -> {
            mainContent(appViewState.data)
        }
    }
}

@Composable
private fun DefaultLoadingContent() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun DefaultErrorView(repeatLoading: (() -> Unit)? = null) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = LOADING_ERROR,
                style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.error)
            )
            repeatLoading?.let { onClickRepeat ->
                Button(
                    onClick = onClickRepeat,
                    modifier = Modifier.padding(top = 24.dp)
                ) {
                    Text(text = REPEAT, style = MaterialTheme.typography.body1)
                }
            }
        }
    }
}
