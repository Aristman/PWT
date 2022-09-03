package ru.marslab.pocketwordtranslator.presentation.theme

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val primaryColor = Color(0xFF626A70)
val primaryLightColor = Color(0xFF9A9DA0)
val primaryDarkColor = Color(0xFF494F53)
val secondaryColor = Color(0xFF54AA3E)
val secondaryLightColor = Color(0xFF78EF5A)
val secondaryDarkColor = Color(0xFF258B0B)
val onPrimaryLightColor = Color(0xFF000000)
val onPrimaryDarkColor = Color(0xFFFFFFFF)
val onSecondaryLightColor = Color(0xFF000000)
val onSecondaryDarkColor = Color(0xFFFFFFFF)
val errorColor = Color(0xFFf50057)
val onErrorColor = Color(0xFF000000)
val surfaceLightColor = Color(0xFFA4ADB5)
val onSurfaceLightColor = Color(0xFF000000)
val surfaceDarkColor = Color(0xFF757E88)
val onSurfaceDarkColor = Color(0xFF000000)
val backgroundLightColor = Color(0xFFFFFFFF)
val onBackgroundLightColor = Color(0xFF000000)
val backgroundDarkColor = Color(0xFF000000)
val onBackgroundDarkColor = Color(0xFFFFFFFF)

val pwtLightGray = Color(0xFFE4E4E4)
val pwtGray = Color(0xFFA7A7A7)
val white50 = Color(0x80FFFFFF)
val white80 = Color(0xCCFFFFFF)

internal val darkColors = darkColors(
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

internal val lightColors = lightColors(
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

internal data class PwtColors(
    val background: Color,
    val white50: Color,
    val white80: Color
)

internal val darkPwtColors = PwtColors(
    background = surfaceDarkColor,
    white50 = white50,
    white80 = white80
)

internal val lightPwtColors = PwtColors(
    background = surfaceLightColor,
    white50 = white50,
    white80 = white80
)

internal fun getColorPalette(darkTheme: Boolean): Colors =
    if (darkTheme) {
        darkColors
    } else {
        lightColors
    }

internal fun pwtColors(darkTheme: Boolean): PwtColors =
    if (darkTheme) {
        darkPwtColors
    } else {
        lightPwtColors
    }
