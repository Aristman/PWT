package ru.marslab.pocketwordtranslator.presentation.theme

import androidx.compose.ui.graphics.Brush

data class PwtBrash(
    val background: Brush,
    val bottomBarBackground: Brush
)

internal val pwtBrash = PwtBrash(
    background = Brush.verticalGradient(
        listOf(pwtLightGray, pwtGray)
    ),
    bottomBarBackground = Brush.verticalGradient(
        listOf(primaryColor, primaryLightColor)
    )
)
