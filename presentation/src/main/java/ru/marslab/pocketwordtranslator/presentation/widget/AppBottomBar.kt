package ru.marslab.pocketwordtranslator.presentation.widget

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.marslab.pocketwordtranslator.core.Action
import ru.marslab.pocketwordtranslator.core.BaseWidgetModel
import ru.marslab.pocketwordtranslator.presentation.R
import ru.marslab.pocketwordtranslator.presentation.common.model.BottomNavigationAction
import ru.marslab.pocketwordtranslator.presentation.feature.home.HomeScreen
import ru.marslab.pocketwordtranslator.presentation.theme.GetLocalProperties
import ru.marslab.pocketwordtranslator.presentation.theme.LocalShapes

class AppBottomBarWidgetModel :
    BaseWidgetModel<AppBottomBarWidgetModel.AppBottomBarState, Action>(AppBottomBarState()) {

    fun setActiveScreen(screeKey: String) {
        setState { state.value.copy(activeScreen = screeKey) }
    }

    @Immutable
    data class AppBottomBarState(
        val activeScreen: String = ""
    )
}

@Composable
fun AppBottomBar(widgetModel: AppBottomBarWidgetModel) {
    GetLocalProperties { dimens, brash, colors, shapes, types ->
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimens.bottomSheetHeight)
                .clip(shapes.bottomSheet)
                .background(brush = brash.bottomBarBackground)
        ) {
            BottomImageButton(R.drawable.ic_arrow_right) {
                val screeKey = HomeScreen().key
                widgetModel.setActiveScreen(screeKey)
                widgetModel.sendAction(BottomNavigationAction(screeKey))
            }
            BottomImageButton(R.drawable.ic_cards) {
//            widgetModel.sendAction(BottomNavigationAction(1))
            }
            BottomImageButton(R.drawable.ic_setting) {
//            widgetModel.sendAction(BottomNavigationAction(2))
            }
        }
    }
}

@Composable
private fun BottomImageButton(
    @DrawableRes imageId: Int,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(LocalShapes.current.button)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = contentDescription
        )
    }
}
