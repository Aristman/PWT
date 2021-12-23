package ru.marslab.shared.domain.model

data class Translations(
    val id: Int,
    val originalWord: String,
    val translations: List<Translate>
)
