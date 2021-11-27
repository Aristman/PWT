package com.example.poketwordtranslator.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WordNW(
    @SerialName("id")
    val id: Int,
    @SerialName("meanings")
    val meanings: List<Meaning>,
    @SerialName("text")
    val text: String
) {
    @Serializable
    data class Meaning(
        @SerialName("id")
        val id: Int,
        @SerialName("imageUrl")
        val imageUrl: String,
        @SerialName("partOfSpeechCode")
        val partOfSpeechCode: String,
        @SerialName("previewUrl")
        val previewUrl: String,
        @SerialName("soundUrl")
        val soundUrl: String,
        @SerialName("transcription")
        val transcription: String,
        @SerialName("translation")
        val translation: Translation
    ) {
        @Serializable
        data class Translation(
            @SerialName("note")
            val note: String?,
            @SerialName("text")
            val text: String
        )
    }
}