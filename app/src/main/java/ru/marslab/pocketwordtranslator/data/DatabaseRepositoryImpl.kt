package ru.marslab.pocketwordtranslator.data

import ru.marslab.pocketwordtranslator.data.room.MainDatabase
import ru.marslab.pocketwordtranslator.domain.repository.DatabaseRepository
import ru.marslab.shared.domain.model.HistoryWord

class DatabaseRepositoryImpl(
    database: MainDatabase
) : DatabaseRepository {
    private val historyDao = database.historyDao()
    private val likeDao = database.likeDao()

    override suspend fun saveToHistory(word: HistoryWord) {
        historyDao.addWord(word.toHistoryDB())
    }
}
