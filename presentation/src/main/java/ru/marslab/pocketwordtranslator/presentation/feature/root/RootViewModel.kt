package ru.marslab.pocketwordtranslator.presentation.feature.root

import ru.marslab.pocketwordtranslator.core.Action
import ru.marslab.pocketwordtranslator.core.BaseViewModel
import ru.marslab.pocketwordtranslator.core.BaseWidgetModel
import ru.marslab.pocketwordtranslator.core.Event
import ru.marslab.pocketwordtranslator.presentation.feature.root.model.RootState
import ru.marslab.pocketwordtranslator.presentation.widget.AppBottomBarWidgetModel

class RootViewModel : BaseViewModel<RootState, Event, Action>(RootState()) {
    val appBottomBarWidgetModel = AppBottomBarWidgetModel()

    override val widgets: List<BaseWidgetModel<*, out Action>> = listOf(
        appBottomBarWidgetModel
    )

    override fun reduceStateByAction(currentState: RootState, action: Action): RootState =
        currentState
}
