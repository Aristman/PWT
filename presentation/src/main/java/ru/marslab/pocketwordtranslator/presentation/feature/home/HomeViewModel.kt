package ru.marslab.pocketwordtranslator.presentation.feature.home

import kotlinx.coroutines.flow.catch
import ru.marslab.pocketwordtranslator.core.Action
import ru.marslab.pocketwordtranslator.core.BaseViewModel
import ru.marslab.pocketwordtranslator.core.BaseWidgetModel
import ru.marslab.pocketwordtranslator.domain.model.switch
import ru.marslab.pocketwordtranslator.domain.usecase.GetHistoryUseCase
import ru.marslab.pocketwordtranslator.domain.usecase.GetWordOfDayUseCase
import ru.marslab.pocketwordtranslator.presentation.feature.home.model.HomeAction
import ru.marslab.pocketwordtranslator.presentation.feature.home.model.HomeEvent
import ru.marslab.pocketwordtranslator.presentation.feature.home.model.HomeState
import ru.marslab.pocketwordtranslator.presentation.widget.HistoryCardWidgetModel
import ru.marslab.pocketwordtranslator.presentation.widget.TranslationFieldWidgetModel
import ru.marslab.pocketwordtranslator.presentation.widget.WordOfDayCardWidgetModel

class HomeViewModel(
    private val getWordOfDay: GetWordOfDayUseCase,
    private val getHistory: GetHistoryUseCase
) : BaseViewModel<HomeState, HomeEvent, HomeAction>(HomeState()) {
    val translationFieldWidgetModel = TranslationFieldWidgetModel()
    val wordOfDayCardWidgetModel = WordOfDayCardWidgetModel()
    val historyCardWidgetModel = HistoryCardWidgetModel()

    init {
        loadWordOfDay()
        loadHistory()
    }

    private fun loadHistory() {
        launch {
            getHistory()
                .catch { handleError(it) }
                .collect(historyCardWidgetModel::setHistory)
        }
    }

    private fun loadWordOfDay() {
        launch {
            getWordOfDay()
                .catch { handleError(it) }
                .collect(wordOfDayCardWidgetModel::setWord)
        }
    }

    override val widgets: List<BaseWidgetModel<*, out Action>> = listOf(
        translationFieldWidgetModel,
        wordOfDayCardWidgetModel,
        historyCardWidgetModel
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
            is HomeAction.WordOfDayClick -> {
                currentState
            }
            is HomeAction.HistoryWordClick -> {
                currentState
            }
        }
}
