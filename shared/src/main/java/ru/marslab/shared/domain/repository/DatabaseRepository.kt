package ru.marslab.shared.domain.repository

import ru.marslab.shared.domain.model.HistoryWord

interface DatabaseRepository {
    suspend fun saveToHistory(word: HistoryWord)
}
