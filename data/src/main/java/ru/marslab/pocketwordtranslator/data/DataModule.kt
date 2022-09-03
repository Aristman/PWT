package ru.marslab.pocketwordtranslator.data

import org.kodein.di.DI
import org.kodein.di.bindProvider
import ru.marslab.pocketwordtranslator.domain.repository.TranslateRepository

val dataModule = DI.Module(name = "data") {
    bindProvider<TranslateRepository> { TranslateRepositoryImpl() }
}
