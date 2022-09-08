package ru.marslab.pocketwordtranslator.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.marslab.pocketwordtranslator.domain.model.Word
import ru.marslab.pocketwordtranslator.domain.repository.TranslateRepository
import kotlin.random.Random

class TranslateRepositoryMock(
    override val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : TranslateRepository {

    override fun getWordOfDay(): Flow<Word> = flowOf(
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
    )

    override fun getHistory(): Flow<List<Word>> = flowOf(
        (0..10).map { getWord(it) }
    )

    private fun getWord(id: Int? = null) =
        Word(
            russianWord = "Слово $id",
            translates = (0..Random.nextInt(10)).map { "Translate $id-$it" }
        )
}
