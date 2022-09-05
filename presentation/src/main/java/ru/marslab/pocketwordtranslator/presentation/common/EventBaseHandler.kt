package ru.marslab.pocketwordtranslator.presentation.common

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ru.marslab.pocketwordtranslator.core.Event
import ru.marslab.pocketwordtranslator.presentation.common.model.BaseAppEvent
import ru.marslab.pocketwordtranslator.presentation.theme.GetLocalProperties

@Composable
fun EventBaseHandler(
    modifier: Modifier = Modifier,
    event: Event?,
    content: @Composable ((paddingValue: PaddingValues) -> Unit)
) {
    val context = LocalContext.current
    GetLocalProperties { _, _, colors, shapes, types ->
        Scaffold(
            modifier = modifier,
            backgroundColor = Color.Transparent,
            scaffoldState = rememberScaffoldState(),
            snackbarHost = {
                if (event is BaseAppEvent.SnackBarEvent) {
                    Snackbar(backgroundColor = colors.snackBarColor, shape = shapes.snackBar) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            style = types.snackBar.copy(color = colors.snackBarTextColor),
                            text = event.resId?.let { stringResource(id = it) }
                                ?: event.message.orEmpty(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        ) {
            content(it)
        }
    }
    LaunchedEffect(key1 = event) {
        if (event is BaseAppEvent.ToastEvent) {
            val message = event.resId?.let { context.getString(it) }
                ?: event.message.orEmpty()
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}
