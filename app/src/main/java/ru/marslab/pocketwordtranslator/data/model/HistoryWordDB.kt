package ru.marslab.pocketwordtranslator.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_words")
data class HistoryWordDB(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "word") val word: String,
    @ColumnInfo(name = "save_time") val saveTime: Long
)
