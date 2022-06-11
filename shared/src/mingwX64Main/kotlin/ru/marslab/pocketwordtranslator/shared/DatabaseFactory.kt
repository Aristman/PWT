package ru.marslab.pocketwordtranslator.shared

import com.squareup.sqldelight.db.SqlDriver
import ru.marslab.pocketwordtranslator.shared.db.AppDatabase

internal actual class DatabaseFactory {
    actual fun createDriver(): SqlDriver {
        TODO("Not yet implemented")
    }

    actual operator fun invoke(): AppDatabase {
        TODO("Not yet implemented")
    }
}
