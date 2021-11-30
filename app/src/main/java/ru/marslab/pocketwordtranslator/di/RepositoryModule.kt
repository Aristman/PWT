package ru.marslab.pocketwordtranslator.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.marslab.pocketwordtranslator.data.NetworkRepositoryImpl
import ru.marslab.pocketwordtranslator.data.retrofit.PwtApi
import ru.marslab.pocketwordtranslator.domain.repository.NetworkRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {

    @Provides
    @Singleton
    fun provideNetworkRepository(api: PwtApi): NetworkRepository =
        NetworkRepositoryImpl(api)
}