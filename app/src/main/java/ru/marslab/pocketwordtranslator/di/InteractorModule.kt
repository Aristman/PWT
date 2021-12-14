package ru.marslab.pocketwordtranslator.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.marslab.pocketwordtranslator.data.room.MainDatabase
import ru.marslab.pocketwordtranslator.domain.DatabaseRepository
import ru.marslab.pocketwordtranslator.domain.interactor.HistoryInteractor
import ru.marslab.pocketwordtranslator.domain.interactor.HistoryInteractorImpl
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
    fun provideTranslateInteractor(
        networkRepository: NetworkRepository,
        databaseRepository: DatabaseRepository
    ): TranslationInteractor =
        TranslationInteractorImpl(networkRepository, databaseRepository)

    @Provides
    @Singleton
    fun provideSoundInteractor(
        networkRepository: NetworkRepository,
        fileRepository: FileRepository
    ): SoundInteractor =
        SoundInteractorImpl(networkRepository, fileRepository)

    @Provides
    @Singleton
    fun provideHistoryInteractor(database: MainDatabase): HistoryInteractor =
        HistoryInteractorImpl(database)
}