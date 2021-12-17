package ru.marslab.pocketwordtranslator.data

import ru.marslab.pocketwordtranslator.data.room.MainDatabase
import ru.marslab.pocketwordtranslator.data.toHistoryDB
import ru.marslab.pocketwordtranslator.domain.model.HistoryWord
import ru.marslab.pocketwordtranslator.domain.repository.DatabaseRepository

class DatabaseRepositoryImpl(
    database: MainDatabase
) : DatabaseRepository {
    private val historyDao = database.historyDao()
    private val likeDao = database.likeDao()

    override suspend fun saveToHistory(word: HistoryWord) {
        historyDao.addWord(word.toHistoryDB())
    }
}