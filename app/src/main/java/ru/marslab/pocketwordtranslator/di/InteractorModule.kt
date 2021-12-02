package ru.marslab.pocketwordtranslator.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.marslab.pocketwordtranslator.domain.interactor.Interactor
import ru.marslab.pocketwordtranslator.domain.interactor.InteractorImpl
import ru.marslab.pocketwordtranslator.domain.model.Translations
import ru.marslab.pocketwordtranslator.domain.repository.NetworkRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object InteractorModule {

    @Provides
    @Singleton
    fun provideTranslateInteractor(networkRepository: NetworkRepository): Interactor<Translations> =
        InteractorImpl(networkRepository)
}