package ru.marslab.pocketwordtranslator.presentation.di

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import ru.marslab.pocketwordtranslator.presentation.feature.home.HomeViewModel
import ru.marslab.pocketwordtranslator.presentation.feature.root.RootViewModel

val viewModelModule = DI.Module(name = "viewModels") {
    bindSingleton {
        HomeViewModel(
            getWordOfDay = instance(),
            getHistory = instance()
        )
    }
    bindSingleton {
        RootViewModel()
    }
}
