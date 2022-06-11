package ru.marslab.pocketwordtranslator.shared

import com.squareup.sqldelight.db.SqlDriver
import ru.marslab.pocketwordtranslator.shared.db.AppDatabase

internal expect class DatabaseFactory {
    fun createDriver(): SqlDriver
    operator fun invoke(): AppDatabase
}
