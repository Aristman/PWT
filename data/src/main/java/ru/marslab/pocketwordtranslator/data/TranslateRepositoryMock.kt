package ru.marslab.pocketwordtranslator.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.marslab.pocketwordtranslator.domain.model.Word
import ru.marslab.pocketwordtranslator.domain.repository.TranslateRepository

class TranslateRepositoryMock(
    override val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : TranslateRepository {

    override fun getWordOfDay(): Flow<Word> = flow {
        Word(
            russianWord = "Деньги",
            translates = listOf(
                "Money",
                "Cash",
                "Bucks",
                "Babuly",
                "Gold"
            )
        )
    }
}
