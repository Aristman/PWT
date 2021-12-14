package ru.marslab.pocketwordtranslator.domain

import ru.marslab.pocketwordtranslator.data.room.MainDatabase
import ru.marslab.pocketwordtranslator.data.toHistoryDB
import ru.marslab.pocketwordtranslator.domain.model.Translations
import ru.marslab.pocketwordtranslator.domain.model.WordHistory

class DatabaseRepositoryImpl(
    database: MainDatabase
) : DatabaseRepository {
    private val historyDao = database.historyDao()
    private val likeDao = database.likeDao()

    override suspend fun saveToHistory(word: WordHistory) {
        historyDao.addWord(word.toHistoryDB())
    }
}