package ru.marslab.pocketwordtranslator.presentation.feature.home.model

import ru.marslab.pocketwordtranslator.core.Action
import ru.marslab.pocketwordtranslator.domain.model.Language

sealed class HomeAction : Action {
    data class TranslateClick(val word: String) : HomeAction()
    object LanguageClick : HomeAction()
    data class HistoryClick(val word: String, val language: Language) : HomeAction()
    data class WordOfDayClick(val word: String) : HomeAction()
    data class HistoryWordClick(val word: String, val language: Language) : HomeAction()

    override fun equals(other: Any?): Boolean = false
    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
