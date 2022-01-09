package ru.marslab.pocketwordtranslator.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "like_translations")
data class LikeTranslationDB(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "translation") val translation: String,
    @ColumnInfo(name = "wordId") val wordId: Int,
    @ColumnInfo(name = "word") val originalWord: String
)
