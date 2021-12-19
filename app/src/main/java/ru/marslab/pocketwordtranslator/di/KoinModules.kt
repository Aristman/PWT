package ru.marslab.pocketwordtranslator.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.marslab.pocketwordtranslator.data.DatabaseRepositoryImpl
import ru.marslab.pocketwordtranslator.data.FileRepositoryImpl
import ru.marslab.pocketwordtranslator.data.NetworkRepositoryImpl
import ru.marslab.pocketwordtranslator.data.okhttp.PwtOkHttp
import ru.marslab.pocketwordtranslator.data.retrofit.PwtRetrofit
import ru.marslab.pocketwordtranslator.data.room.PwtDatabase
import ru.marslab.pocketwordtranslator.domain.interactor.HistoryInteractor
import ru.marslab.pocketwordtranslator.domain.interactor.HistoryInteractorImpl
import ru.marslab.pocketwordtranslator.domain.interactor.SoundInteractor
import ru.marslab.pocketwordtranslator.domain.interactor.SoundInteractorImpl
import ru.marslab.pocketwordtranslator.domain.interactor.TranslationInteractor
import ru.marslab.pocketwordtranslator.domain.interactor.TranslationInteractorImpl
import ru.marslab.pocketwordtranslator.domain.repository.DatabaseRepository
import ru.marslab.pocketwordtranslator.domain.repository.FileRepository
import ru.marslab.pocketwordtranslator.domain.repository.NetworkRepository
import ru.marslab.pocketwordtranslator.presentation.viewmodels.HistoryViewModel
import ru.marslab.pocketwordtranslator.presentation.viewmodels.SoundViewModel
import ru.marslab.pocketwordtranslator.presentation.viewmodels.TranslationViewModel

val okHttpModule = module {
    single { PwtOkHttp.createClient() }
    factory { (url: String) -> PwtOkHttp.getCall(client = get(), url) }
}

val retrofitModule = module {
    single { PwtRetrofit.createRetrofit(client = get()) }
    single { PwtRetrofit.createPwtApi(retrofit = get()) }
}

val databaseModule = module {
    single { PwtDatabase.createDatabase(context = get()) }
}

val repositoryModule = module {
    single<FileRepository> { FileRepositoryImpl(context = get()) }
    single<NetworkRepository> { NetworkRepositoryImpl(translateService = get()) }
    single<DatabaseRepository> { DatabaseRepositoryImpl(database = get()) }
}

val interactorModule = module {
    single<TranslationInteractor> {
        TranslationInteractorImpl(
            networkRepository = get(),
            databaseRepository = get()
        )
    }
    single<SoundInteractor> {
        SoundInteractorImpl(
            networkRepository = get(),
            fileRepository = get()
        )
    }
    single<HistoryInteractor> { HistoryInteractorImpl(database = get()) }
}

val viewModelModule = module {
    viewModel { HistoryViewModel(historyInteractor = get()) }
    viewModel { SoundViewModel(soundInteractor = get()) }
    viewModel { TranslationViewModel(translationInteractor = get()) }
}
