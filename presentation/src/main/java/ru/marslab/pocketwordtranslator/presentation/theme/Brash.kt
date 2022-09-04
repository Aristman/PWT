package ru.marslab.pocketwordtranslator.presentation.theme

import androidx.compose.ui.graphics.Brush

data class PwtBrash(
    val background: Brush
)

internal val pwtBrash = PwtBrash(
    background = Brush.verticalGradient(
        listOf(pwtLightGray, pwtGray)
    )
)
