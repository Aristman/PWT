package ru.marslab.pocketwordtranslator.presentation.feature.home

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.marslab.pocketwordtranslator.core.BaseViewModel
import ru.marslab.pocketwordtranslator.presentation.feature.home.model.HomeAction
import ru.marslab.pocketwordtranslator.presentation.feature.home.model.HomeEvent
import ru.marslab.pocketwordtranslator.presentation.feature.home.model.HomeState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() :
    BaseViewModel<HomeState, HomeEvent, HomeAction>(HomeState()) {
    override fun reduceStateByAction(
        currentState: HomeState,
        action: HomeAction
    ): HomeState =
        when (action) {
            HomeAction.TestClick -> currentState.copy(
                word = if (currentState.word.isEmpty()) {
                    "click ON"
                } else {
                    ""
                }
            )
        }
}
