package ru.marslab.pocketwordtranslator.di

import org.koin.dsl.module
import ru.marslab.pocketwordtranslator.data.FileRepositoryImpl
import ru.marslab.pocketwordtranslator.data.NetworkRepositoryImpl
import ru.marslab.pocketwordtranslator.data.okhttp.PwtOkHttp
import ru.marslab.pocketwordtranslator.data.retrofit.PwtRetrofit
import ru.marslab.pocketwordtranslator.domain.interactor.SoundInteractor
import ru.marslab.pocketwordtranslator.domain.interactor.SoundInteractorImpl
import ru.marslab.pocketwordtranslator.domain.interactor.TranslationInteractor
import ru.marslab.pocketwordtranslator.domain.interactor.TranslationInteractorImpl
import ru.marslab.pocketwordtranslator.domain.repository.FileRepository
import ru.marslab.pocketwordtranslator.domain.repository.NetworkRepository

val okHttpModule = module {
    single { PwtOkHttp.createClient() }
    factory { (url: String) -> PwtOkHttp.getCall(client = get(), url) }
}

val retrofitModule = module {
    single { PwtRetrofit.createRetrofit(client = get()) }
    single { PwtRetrofit.createPwtApi(retrofit = get()) }
}

val repositoryModule = module {
    single<FileRepository> { FileRepositoryImpl(context = get()) }
    single<NetworkRepository> { NetworkRepositoryImpl(translateService = get()) }
}

val interactorModule = module {
    single<TranslationInteractor> { TranslationInteractorImpl(networkRepository = get()) }
    single<SoundInteractor> {
        SoundInteractorImpl(
            networkRepository = get(),
            fileRepository = get()
        )
    }
}