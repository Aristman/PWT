package ru.marslab.pocketwordtranslator.presentation.translation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TranslationUiState(
    val id: Int,
    val word: String,
    val translation: List<String>,
    val image: String,
    val preview: String,
    val transcription: String,
    val sound: String
) : Parcelable
