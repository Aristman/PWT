package ru.marslab.pocketwordtranslator.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TranslateWordUi(
    val id : Int,
    val word: String,
    val translation: List<String>,
    val image: String,
    val preview: String,
    val transcription: String,
    val sound: String
) : Parcelable
