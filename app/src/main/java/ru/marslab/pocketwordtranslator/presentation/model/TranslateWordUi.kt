package ru.marslab.pocketwordtranslator.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TranslateWordUi(
    val word: String,
    val translation: List<String>,
    val sound: List<String>
) : Parcelable
