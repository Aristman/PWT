package com.example.poketwordtranslator.domain.model

data class Translate(
    val id: Int,
    val image: String,
    val sound: String,
    val transcription: String,
    val translation: String,
    val note: String
)
