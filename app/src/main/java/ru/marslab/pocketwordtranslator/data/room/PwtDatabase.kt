package ru.marslab.pocketwordtranslator.data.room

import android.content.Context
import androidx.room.Room

object PwtDatabase {
    private const val DATABASE_NAME = "pwt_db"

    fun createDatabase(context: Context): MainDatabase =
        Room.databaseBuilder(context, MainDatabase::class.java, DATABASE_NAME)
            .build()
}
