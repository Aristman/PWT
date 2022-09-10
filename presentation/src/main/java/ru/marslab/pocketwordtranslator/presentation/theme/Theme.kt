package ru.marslab.pocketwordtranslator.presentation.theme // ktlint-disable filename

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun PocketWordTranslatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    LocalContentProvider() {
        MaterialTheme(
            colors = getColorPalette(darkTheme),
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}
