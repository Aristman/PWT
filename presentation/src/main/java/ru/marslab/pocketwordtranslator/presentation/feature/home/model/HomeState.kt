package ru.marslab.pocketwordtranslator.presentation.feature.home.model

import androidx.compose.runtime.Immutable
import ru.marslab.pocketwordtranslator.domain.model.Language

@Immutable
data class HomeState(
    val word: String = "",
    val language: Language = Language.Rus,
    val wordOfDay: String = "",
    val history: List<String> = emptyList()
)
