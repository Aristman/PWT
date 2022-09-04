package ru.marslab.pocketwordtranslator.presentation.theme // ktlint-disable filename

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush

fun Modifier.appBackground(): Modifier =
    Modifier.then(background(Brush.verticalGradient(listOf(pwtLightGray, pwtGray))))
