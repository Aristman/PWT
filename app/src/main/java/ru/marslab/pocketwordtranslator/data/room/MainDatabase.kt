package ru.marslab.pocketwordtranslator.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.marslab.pocketwordtranslator.data.model.HistoryWordDB
import ru.marslab.pocketwordtranslator.data.model.LikeTranslationDB

@Database(
    entities = [HistoryWordDB::class, LikeTranslationDB::class],
    version = 1,
    exportSchema = true
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
    abstract fun likeDao(): LikeDao
}
