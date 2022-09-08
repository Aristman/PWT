package ru.marslab.pocketwordtranslator.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(0.dp)
)

data class PwtShapes(
    val editTextField: Shape,
    val card: Shape,
    val snackBar: Shape,
    val bottomSheet: Shape,
    val button: Shape
)

internal val pwtShapes = PwtShapes(
    editTextField = RoundedCornerShape(16.dp),
    card = RoundedCornerShape(16.dp),
    snackBar = RectangleShape,
    bottomSheet = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    button = RoundedCornerShape(8.dp)
)
