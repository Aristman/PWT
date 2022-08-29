package ru.marslab.pocketwordtranslator.presentation.feature.home

import ru.marslab.pocketwordtranslator.core.Action
import ru.marslab.pocketwordtranslator.core.BaseViewModel
import ru.marslab.pocketwordtranslator.core.BaseWidgetModel
import ru.marslab.pocketwordtranslator.domain.model.switch
import ru.marslab.pocketwordtranslator.presentation.feature.home.model.HomeAction
import ru.marslab.pocketwordtranslator.presentation.feature.home.model.HomeEvent
import ru.marslab.pocketwordtranslator.presentation.feature.home.model.HomeState
import ru.marslab.pocketwordtranslator.presentation.widget.TranslationFieldWidgetModel

class HomeViewModel : BaseViewModel<HomeState, HomeEvent, HomeAction>(HomeState()) {
    val translationFieldWidgetModel = TranslationFieldWidgetModel()

    override val widgets: List<BaseWidgetModel<*, out Action>> = listOf(
        translationFieldWidgetModel
    ).actionObserve()

    override fun reduceStateByAction(
        currentState: HomeState,
        action: HomeAction
    ): HomeState =
        when (action) {
            is HomeAction.HistoryClick -> {
                currentState
            }
            is HomeAction.TranslateClick -> {
                currentState
            }
            is HomeAction.LanguageClick -> {
                translationFieldWidgetModel.switchLanguage()
                currentState.copy(language = currentState.language.switch())
            }
        }
}
