package ru.marslab.pocketwordtranslator.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.marslab.pocketwordtranslator.domain.repository.TranslateRepository

class TranslateRepositoryImpl(
    override val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    mockRepositoryMock: TranslateRepositoryMock = TranslateRepositoryMock()
) : TranslateRepository by mockRepositoryMock
