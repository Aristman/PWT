package ru.marslab.pocketwordtranslator.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.marslab.pocketwordtranslator.presentation.R

private val appBaseFont = FontFamily(
    Font(R.font.montserrat_alternates, weight = FontWeight(600))
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = appBaseFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

internal val snackBarTypeStyle = TextStyle(
    fontFamily = appBaseFont,
    fontSize = 14.sp
)

data class PwtTypes(
    val snackBar: TextStyle
)

internal val pwtTypes = PwtTypes(
    snackBar = snackBarTypeStyle
)
