package ru.marslab.pocketwordtranslator.shared

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import ru.marslab.pocketwordtranslator.shared.db.AppDatabase

internal actual class DatabaseFactory(
    private val context: Context,
    private val databaseName: String
) {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(schema = AppDatabase.Schema, context = context, name = databaseName)

    actual operator fun invoke(): AppDatabase =
        AppDatabase(createDriver())
}
