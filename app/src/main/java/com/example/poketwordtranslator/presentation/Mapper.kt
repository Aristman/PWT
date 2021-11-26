package com.example.poketwordtranslator.presentation

import com.example.poketwordtranslator.domain.model.Translations
import com.example.poketwordtranslator.presentation.model.TranslateWordUi

fun Translations.toUi(): TranslateWordUi =
    TranslateWordUi(
        word = originalWord,
        translation = this.translations.first().translation
    )