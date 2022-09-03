package ru.marslab.pocketwordtranslator.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.marslab.pocketwordtranslator.domain.model.Word
import ru.marslab.pocketwordtranslator.domain.repository.TranslateRepository

class GetWordOfDayUseCase(
    private val translateRepository: TranslateRepository
) {
    operator fun invoke(): Flow<Word> =
        translateRepository.getWordOfDay()
}
