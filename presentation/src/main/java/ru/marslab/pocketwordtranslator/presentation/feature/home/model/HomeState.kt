package ru.marslab.pocketwordtranslator.presentation.feature.home.model

import javax.annotation.concurrent.Immutable

@Immutable
data class HomeState(
    val word: String = ""
)
