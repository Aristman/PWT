package ru.marslab.pocketwordtranslator.domain.model

data class Translate(
    val id: Int,
    val image: String,
    val preview: String,
    val sound: String,
    val transcription: String,
    val translation: String,
    val note: String
)
