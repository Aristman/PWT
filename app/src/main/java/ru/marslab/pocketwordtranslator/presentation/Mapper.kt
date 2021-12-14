package ru.marslab.pocketwordtranslator.presentation

import ru.marslab.pocketwordtranslator.domain.model.Translations
import ru.marslab.pocketwordtranslator.domain.model.WordHistory
import ru.marslab.pocketwordtranslator.presentation.model.TranslateWordUi

fun Translations.toUi(): TranslateWordUi =
    TranslateWordUi(
        id = id,
        word = originalWord,
        translation = translations.map { it.translation },
        sound = translations.map { it.sound }
    )

fun TranslateWordUi.toDomainHistory(): WordHistory =
    WordHistory(
        id = id,
        word = word
    )