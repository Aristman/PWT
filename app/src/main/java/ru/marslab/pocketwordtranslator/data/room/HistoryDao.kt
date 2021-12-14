package ru.marslab.pocketwordtranslator.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import ru.marslab.pocketwordtranslator.data.model.HistoryWordDB

@Dao
interface HistoryDao {
    @Insert(onConflict = REPLACE)
    fun addWord(word: HistoryWordDB)

    @Query("SELECT * FROM history_words")
    fun getHistory(): Observable<HistoryWordDB>

    @Query("DELETE FROM history_words")
    fun clearHistory()
}