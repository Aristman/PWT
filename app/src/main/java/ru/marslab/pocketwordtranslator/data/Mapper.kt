package ru.marslab.pocketwordtranslator.data

import ru.marslab.pocketwordtranslator.data.model.HistoryWordDB
import ru.marslab.pocketwordtranslator.data.model.WordNW
import ru.marslab.pocketwordtranslator.domain.model.HistoryWord
import ru.marslab.pocketwordtranslator.domain.model.Translate
import ru.marslab.pocketwordtranslator.domain.model.Translations
import java.util.Date

fun WordNW.toDomain(): Translations =
    Translations(
        id = id,
        originalWord = text,
        translations = this.meanings.map { meaning ->
            Translate(
                id = meaning.id,
                image = meaning.imageUrl,
                preview = meaning.previewUrl,
                sound = meaning.soundUrl,
                transcription = meaning.transcription,
                translation = meaning.translation.text,
                note = meaning.translation.note ?: ""
            )
        }
    )

fun HistoryWord.toHistoryDB(): HistoryWordDB =
    HistoryWordDB(
        id = id,
        word = word,
        saveTime = Date().time
    )

fun HistoryWordDB.toDomain(): HistoryWord =
    HistoryWord(
        id = id,
        word = word
    )
