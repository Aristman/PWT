package ru.marslab.pocketwordtranslator.data

import ru.marslab.pocketwordtranslator.data.model.WordNW
import ru.marslab.pocketwordtranslator.domain.model.Translate
import ru.marslab.pocketwordtranslator.domain.model.Translations

fun WordNW.toDomain(): Translations =
    Translations(
        id = id,
        originalWord = text,
        translations = this.meanings.map { meaning ->
            Translate(
                id = meaning.id,
                image = meaning.imageUrl,
                sound = meaning.soundUrl,
                transcription = meaning.transcription,
                translation = meaning.translation.text,
                note = meaning.translation.note ?: ""
            )
        }
    )
