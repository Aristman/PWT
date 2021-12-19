package ru.marslab.pocketwordtranslator.domain.repository

import ru.marslab.pocketwordtranslator.domain.model.HistoryWord

interface DatabaseRepository {
    suspend fun saveToHistory(word: HistoryWord)
}
