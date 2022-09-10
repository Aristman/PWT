package ru.marslab.pocketwordtranslator.presentation.feature.root

import ru.marslab.pocketwordtranslator.core.Action
import ru.marslab.pocketwordtranslator.core.BaseViewModel
import ru.marslab.pocketwordtranslator.core.BaseWidgetModel
import ru.marslab.pocketwordtranslator.core.Event
import ru.marslab.pocketwordtranslator.presentation.common.model.BottomNavigationAction
import ru.marslab.pocketwordtranslator.presentation.feature.root.model.RootState
import ru.marslab.pocketwordtranslator.presentation.navigation.NavGraph
import ru.marslab.pocketwordtranslator.presentation.widget.AppBottomBarWidgetModel
import ru.marslab.pocketwordtranslator.presentation.widget.MainFABWidgetModel

class RootViewModel : BaseViewModel<RootState, Event, Action>(RootState()) {
    val appBottomBarWidgetModel = AppBottomBarWidgetModel()
    val mainFABWidgetModel = MainFABWidgetModel()

    override val widgets: List<BaseWidgetModel<*, out Action>> = listOf(
        appBottomBarWidgetModel,
        mainFABWidgetModel
    ).actionObserve()

    override fun reduceStateByAction(currentState: RootState, action: Action): RootState {
        when (action) {
            BottomNavigationAction.HomeScreen -> navigator.push(NavGraph.HomeDestination.screen)
        }
        return currentState
    }
}
