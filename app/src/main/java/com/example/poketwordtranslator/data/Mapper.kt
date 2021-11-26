package com.example.poketwordtranslator.data

import com.example.poketwordtranslator.data.model.WordNW
import com.example.poketwordtranslator.domain.model.Translate
import com.example.poketwordtranslator.domain.model.Translations

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
                note = meaning.translation.note
            )
        }
    )
