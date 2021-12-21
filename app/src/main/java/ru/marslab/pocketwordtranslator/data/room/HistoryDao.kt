package ru.marslab.pocketwordtranslator.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.Single
import ru.marslab.pocketwordtranslator.data.model.HistoryWordDB

@Dao
interface HistoryDao {
    @Insert(onConflict = REPLACE)
    fun addWord(word: HistoryWordDB)

    @Query("SELECT * FROM history_words ORDER BY save_time DESC")
    fun getHistory(): Single<List<HistoryWordDB>>

    @Query("DELETE FROM history_words")
    fun clearHistory()
}
