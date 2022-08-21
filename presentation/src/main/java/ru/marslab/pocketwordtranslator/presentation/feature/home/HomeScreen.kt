package ru.marslab.pocketwordtranslator.presentation.feature.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.core.model.rememberScreenModel
import ru.marslab.pocketwordtranslator.presentation.feature.home.model.HomeAction

class HomeScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { HomeScreenModel() }
        Log.d("MYTAG", "Content: $screenModel")
        MainView(screenModel)
    }
}

@Composable
private fun MainView(screenModel: HomeScreenModel) {
    val state = screenModel.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = { screenModel.sendAction(HomeAction.TestClick) }) {
            Text(text = state.value.word)
        }
    }
}
