package ru.marslab.pocketwordtranslator.domain

import ru.marslab.pocketwordtranslator.domain.model.WordHistory

interface DatabaseRepository {
    suspend fun saveToHistory(word: WordHistory)
}