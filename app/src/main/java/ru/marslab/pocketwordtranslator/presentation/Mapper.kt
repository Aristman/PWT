package ru.marslab.pocketwordtranslator.presentation

import ru.marslab.pocketwordtranslator.domain.model.HistoryWord
import ru.marslab.pocketwordtranslator.domain.model.Translations
import ru.marslab.pocketwordtranslator.presentation.model.HistoryWordUi
import ru.marslab.pocketwordtranslator.presentation.model.TranslateWordUi

private const val BASE_SCHEME = "https:"

fun Translations.toUi(): TranslateWordUi =
    TranslateWordUi(
        id = id,
        word = originalWord,
        translation = translations.map { it.translation },
        sound = this.translations.first().sound,
        image = BASE_SCHEME + this.translations.first().image,
        preview = BASE_SCHEME + translations.first().preview,
        transcription = translations.first().transcription
    )

fun TranslateWordUi.toDomainHistory(): HistoryWord =
    HistoryWord(
        id = id,
        word = word
    )

fun HistoryWord.toUi(): HistoryWordUi =
    HistoryWordUi(
        id = id,
        word = word
    )