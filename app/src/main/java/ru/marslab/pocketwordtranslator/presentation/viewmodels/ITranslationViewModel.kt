package ru.marslab.pocketwordtranslator.presentation.viewmodels

import kotlinx.coroutines.flow.StateFlow
import ru.marslab.pocketwordtranslator.presentation.model.AppViewState
import ru.marslab.pocketwordtranslator.presentation.model.TranslateWordUi

interface ITranslationViewModel{
    val translationsState: StateFlow<AppViewState<List<TranslateWordUi>, Throwable>>
    fun getTranslations(word: String)
    fun saveToHistory(word: TranslateWordUi)
}