package ru.marslab.pocketwordtranslator.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

@Composable
internal fun LocalContentProvider(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalBrash provides pwtBrash,
        LocalColors provides pwtColors(darkTheme),
        LocalShapes provides pwtShapes,
        LocalDimens provides pwtDimens
    ) {
        content()
    }
}

internal val LocalBrash = compositionLocalOf { pwtBrash }

internal val LocalColors = compositionLocalOf { lightPwtColors }

internal val LocalShapes = compositionLocalOf { pwtShapes }

internal val LocalDimens = compositionLocalOf { pwtDimens }
