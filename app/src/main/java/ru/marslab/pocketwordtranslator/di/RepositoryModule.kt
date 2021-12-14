package ru.marslab.pocketwordtranslator.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import ru.marslab.pocketwordtranslator.data.FileRepositoryImpl
import ru.marslab.pocketwordtranslator.data.NetworkRepositoryImpl
import ru.marslab.pocketwordtranslator.data.okhttp.PwtOkHttp
import ru.marslab.pocketwordtranslator.data.retrofit.PwtApi
import ru.marslab.pocketwordtranslator.data.room.MainDatabase
import ru.marslab.pocketwordtranslator.domain.DatabaseRepository
import ru.marslab.pocketwordtranslator.domain.DatabaseRepositoryImpl
import ru.marslab.pocketwordtranslator.domain.repository.FileRepository
import ru.marslab.pocketwordtranslator.domain.repository.NetworkRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {

    @Provides
    @Singleton
    fun provideDatabaseRepository(database: MainDatabase): DatabaseRepository =
        DatabaseRepositoryImpl(database = database)

    @Provides
    @Singleton
    fun provideNetworkRepository(api: PwtApi): NetworkRepository =
        NetworkRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideFileRepository(context: Context): FileRepository =
        FileRepositoryImpl(context)

    @Provides
    fun provideContext(app: Application): Context =
        app.applicationContext
}