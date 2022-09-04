package ru.marslab.pocketwordtranslator.domain.di

import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance
import ru.marslab.pocketwordtranslator.domain.usecase.GetHistoryUseCase
import ru.marslab.pocketwordtranslator.domain.usecase.GetWordOfDayUseCase

val domainModule = DI.Module(name = "domain") {
    bindProvider { GetWordOfDayUseCase(translateRepository = instance()) }
    bindProvider { GetHistoryUseCase(translateRepository = instance()) }
}
