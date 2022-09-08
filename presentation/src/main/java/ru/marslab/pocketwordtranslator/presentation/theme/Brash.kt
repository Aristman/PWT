package ru.marslab.pocketwordtranslator.presentation.theme

import androidx.compose.ui.graphics.Brush

data class PwtBrash(
    val background: Brush,
    val bottomBarBackgroun: Brush
)

internal val pwtBrash = PwtBrash(
    background = Brush.verticalGradient(
        listOf(pwtLightGray, pwtGray)
    ),
    bottomBarBackgroun = Brush.verticalGradient(
        listOf(primaryColor, primaryLightColor)
    )
)
