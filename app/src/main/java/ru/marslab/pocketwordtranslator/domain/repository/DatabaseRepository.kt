package ru.marslab.pocketwordtranslator.domain.repository

import ru.marslab.shared.domain.model.HistoryWord

interface DatabaseRepository {
    suspend fun saveToHistory(word: HistoryWord)
}
