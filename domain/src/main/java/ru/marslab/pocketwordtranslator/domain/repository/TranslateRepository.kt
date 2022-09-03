package ru.marslab.pocketwordtranslator.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.marslab.pocketwordtranslator.core.BaseRepository
import ru.marslab.pocketwordtranslator.domain.model.Word

interface TranslateRepository : BaseRepository {

    fun getWordOfDay(): Flow<Word>
}
