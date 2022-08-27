package ru.marslab.pocketwordtranslator.di

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import ru.marslab.pocketwordtranslator.presentation.feature.home.HomeScreenModel

@Module
@InstallIn(SingletonComponent::class)
interface ScreenModule {

    @Binds
    @IntoMap
    @ScreenModelKey(HomeScreenModel::class)
    fun bindHomeScreenModel(homeScreenModel: HomeScreenModel): ScreenModel
}
