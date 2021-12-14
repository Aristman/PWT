package ru.marslab.pocketwordtranslator.domain

import ru.marslab.pocketwordtranslator.domain.model.Translations

interface DatabaseRepository {
    fun saveToHistory(word: Translations)
}