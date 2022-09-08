package ru.marslab.pocketwordtranslator.presentation.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class PwtDimens(
    val contentPadding: Dp,
    val bottomSheetHeight: Dp
)

internal val pwtDimens = PwtDimens(
    contentPadding = 20.dp,
    bottomSheetHeight = 70.dp
)
