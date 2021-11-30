package ru.marslab.pocketwordtranslator.presentation

import ru.marslab.pocketwordtranslator.domain.model.Translations
import ru.marslab.pocketwordtranslator.presentation.model.TranslateWordUi

fun Translations.toUi(): TranslateWordUi =
    TranslateWordUi(
        word = originalWord,
        translation = this.translations.first().translation
    )