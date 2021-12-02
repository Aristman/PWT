package ru.marslab.pocketwordtranslator.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

val darkColors = darkColors(
    primary = primaryColor,
    primaryVariant = primaryDarkColor,
    secondary = secondaryColor,
    secondaryVariant = secondaryDarkColor,
    surface = surfaceDarkColor,
    error = errorColor,
    background = backgroundDarkColor,
    onPrimary = onPrimaryDarkColor,
    onSecondary = onSecondaryDarkColor,
    onSurface = onSurfaceDarkColor,
    onError = onErrorColor,
    onBackground = onBackgroundDarkColor
)

val lightColors = lightColors(
    primary = primaryLightColor,
    primaryVariant = primaryDarkColor,
    secondary = secondaryColor,
    secondaryVariant = secondaryDarkColor,
    surface = surfaceLightColor,
    error = errorColor,
    background = backgroundLightColor,
    onPrimary = onPrimaryLightColor,
    onSecondary = onSecondaryLightColor,
    onSurface = onSurfaceLightColor,
    onError = onErrorColor,
    onBackground = onBackgroundLightColor
)

private val LightColorPalette = lightColors
private val DarkColorPalette = darkColors

@Composable
fun PocketWordTranslatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}