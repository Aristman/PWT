package ru.marslab.pocketwordtranslator.domain.model

data class Word(
    val russianWord: String,
    val translates: List<String>
)
