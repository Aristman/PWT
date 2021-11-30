package ru.marslab.pocketwordtranslator.presentation.viewmodels

import kotlinx.coroutines.flow.StateFlow
import ru.marslab.pocketwordtranslator.domain.model.Translations
import ru.marslab.pocketwordtranslator.presentation.model.AppViewState

interface TranslationViewModel {
    val translations: StateFlow<AppViewState>
    fun getTranslations(word: String)
}