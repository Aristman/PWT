package ru.marslab.pocketwordtranslator.presentation.widget

import androidx.annotation.DrawableRes
import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import ru.marslab.pocketwordtranslator.core.Action
import ru.marslab.pocketwordtranslator.core.BaseWidgetModel

class MainFABWidgetModel :
    BaseWidgetModel<MainFABWidgetModel.MainFABState, Action>(MainFABState()) {

    fun setOnClickListener(onClick: () -> Unit) {
        setState { state.value.copy(onClick = onClick) }
    }

    @Immutable
    data class MainFABState(
        @DrawableRes val image: Int? = null,
        val onClick: (() -> Unit) = {}
    )
}

@Composable
fun MainFloatingActionButton(widgetModel: MainFABWidgetModel) {
    FloatingActionButton(onClick = { /*TODO*/ }) {
    }
}
