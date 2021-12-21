package ru.marslab.pocketwordtranslator.presentation

import ru.marslab.pocketwordtranslator.presentation.model.HistoryUiState
import ru.marslab.pocketwordtranslator.presentation.translation.TranslationUiState
import ru.marslab.shared.domain.model.HistoryWord
import ru.marslab.shared.domain.model.Translations

private const val BASE_SCHEME = "https:"

fun Translations.toUiState(): TranslationUiState =
    TranslationUiState(
        id = id,
        word = originalWord,
        translation = translations.map { it.translation },
        sound = this.translations.first().sound,
        image = BASE_SCHEME + this.translations.first().image,
        preview = BASE_SCHEME + translations.first().preview,
        transcription = translations.first().transcription
    )

fun TranslationUiState.toDomainHistory(): HistoryWord =
    HistoryWord(
        id = id,
        word = word
    )

fun HistoryWord.toUiState(): HistoryUiState =
    HistoryUiState(
        id = id,
        word = word
    )
