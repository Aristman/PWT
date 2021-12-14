package ru.marslab.pocketwordtranslator.domain

import ru.marslab.pocketwordtranslator.domain.model.HistoryWord

interface DatabaseRepository {
    suspend fun saveToHistory(word: HistoryWord)
}