package ru.marslab.pocketwordtranslator.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.marslab.pocketwordtranslator.domain.interactor.SoundInteractor
import ru.marslab.pocketwordtranslator.domain.interactor.SoundInteractorImpl
import ru.marslab.pocketwordtranslator.domain.interactor.TranslationInteractor
import ru.marslab.pocketwordtranslator.domain.interactor.TranslationInteractorImpl
import ru.marslab.pocketwordtranslator.domain.repository.FileRepository
import ru.marslab.pocketwordtranslator.domain.repository.NetworkRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object InteractorModule {

    @Provides
    @Singleton
    fun provideTranslateInteractor(networkRepository: NetworkRepository): TranslationInteractor =
        TranslationInteractorImpl(networkRepository)

    @Provides
    @Singleton
    fun provideSoundInteractor(networkRepository: NetworkRepository, fileRepository: FileRepository): SoundInteractor =
        SoundInteractorImpl(networkRepository, fileRepository)
}