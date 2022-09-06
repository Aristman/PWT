package ru.marslab.pocketwordtranslator.presentation.feature.home

import kotlinx.coroutines.flow.catch
import ru.marslab.pocketwordtranslator.core.Action
import ru.marslab.pocketwordtranslator.core.BaseViewModel
import ru.marslab.pocketwordtranslator.core.BaseWidgetModel
import ru.marslab.pocketwordtranslator.core.Event
import ru.marslab.pocketwordtranslator.domain.model.switch
import ru.marslab.pocketwordtranslator.domain.usecase.GetHistoryUseCase
import ru.marslab.pocketwordtranslator.domain.usecase.GetWordOfDayUseCase
import ru.marslab.pocketwordtranslator.presentation.common.model.BaseAppEvent
import ru.marslab.pocketwordtranslator.presentation.feature.home.model.HomeAction
import ru.marslab.pocketwordtranslator.presentation.feature.home.model.HomeState
import ru.marslab.pocketwordtranslator.presentation.widget.AppBottomBarWidgetModel
import ru.marslab.pocketwordtranslator.presentation.widget.HistoryCardWidgetModel
import ru.marslab.pocketwordtranslator.presentation.widget.TranslationFieldWidgetModel
import ru.marslab.pocketwordtranslator.presentation.widget.WordOfDayCardWidgetModel

class HomeViewModel(
    private val getWordOfDay: GetWordOfDayUseCase,
    private val getHistory: GetHistoryUseCase
) : BaseViewModel<HomeState, Event, HomeAction>(HomeState()) {
    val translationFieldWidgetModel = TranslationFieldWidgetModel()
    val wordOfDayCardWidgetModel = WordOfDayCardWidgetModel()
    val historyCardWidgetModel = HistoryCardWidgetModel()
    val appBottomBarWidgetModel = AppBottomBarWidgetModel()

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
        historyCardWidgetModel,
        appBottomBarWidgetModel
    ).actionObserve()

    override fun reduceStateByAction(
        currentState: HomeState,
        action: Action
    ): HomeState =
        when (action) {
            is HomeAction.TranslateClick -> {
                sendEvent(BaseAppEvent.ToastEvent(message = "Translate Click"))
                currentState
            }
            is HomeAction.LanguageClick -> {
                sendEvent(BaseAppEvent.ToastEvent(message = "Язык - ${currentState.language.name}"))
                translationFieldWidgetModel.switchLanguage()
                currentState.copy(language = currentState.language.switch())
            }
            is HomeAction.WordOfDayClick -> {
                sendEvent(BaseAppEvent.ToastEvent(message = action.word))
                currentState
            }
            is HomeAction.HistoryWordClick -> {
                launch {
                    sendEvent(
                        BaseAppEvent.SnackBarEvent(message = "History Click ${action.word}"),
                        dismissDelay = 2000
                    )
                }
                currentState
            }
            else -> {
                currentState
            }
        }
}
