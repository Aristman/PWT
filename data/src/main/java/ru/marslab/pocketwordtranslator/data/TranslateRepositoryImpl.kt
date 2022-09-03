package ru.marslab.pocketwordtranslator.data

import ru.marslab.pocketwordtranslator.domain.repository.TranslateRepository

class TranslateRepositoryImpl(
    mockRepositoryMock: TranslateRepositoryMock
) : TranslateRepository by mockRepositoryMock
