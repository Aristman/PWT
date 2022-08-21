package ru.marslab.pocketwordtranslator.presentation.feature.home

import ru.marslab.pocketwordtranslator.core.BaseScreenModel
import ru.marslab.pocketwordtranslator.presentation.feature.home.model.HomeAction
import ru.marslab.pocketwordtranslator.presentation.feature.home.model.HomeEvent
import ru.marslab.pocketwordtranslator.presentation.feature.home.model.HomeState

class HomeScreenModel : BaseScreenModel<HomeState, HomeEvent, HomeAction>(HomeState()) {
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
