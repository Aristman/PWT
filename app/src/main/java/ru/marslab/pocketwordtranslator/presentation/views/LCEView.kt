package ru.marslab.pocketwordtranslator.presentation.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.marslab.pocketwordtranslator.presentation.model.AppViewState

private const val LOADING_ERROR = "Ошибка загрузки!"
private const val REPEAT = "Повторить"

@Composable
fun LCEView(
    appViewState: AppViewState,
    initContent: (@Composable () -> Unit)? = null,
    loadingContent: (@Composable () -> Unit)? = null,
    errorContent: (@Composable (e: Throwable) -> Unit)? = null,
    repeatLoading: (() -> Unit)? = null,
    mainContent: @Composable () -> Unit
) {
    when (appViewState) {
        AppViewState.Init -> {
            initContent?.let { it() }
        }
        is AppViewState.Error -> {
            if (errorContent == null) {
                DefaultErrorView(repeatLoading)
            } else {
                errorContent(appViewState.error)
            }
        }
        is AppViewState.Loading -> {
            if (loadingContent == null) {
                DefaultLoadingContent()
            } else {
                loadingContent()
            }
        }
        is AppViewState.Success<*> -> {
            mainContent()
        }
    }
}

@Composable
fun DefaultLoadingContent() {
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
