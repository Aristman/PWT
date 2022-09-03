package ru.marslab.pocketwordtranslator.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.Navigator
import ru.marslab.pocketwordtranslator.navigation.NavGraph
import ru.marslab.pocketwordtranslator.presentation.theme.PocketWordTranslatorTheme
import ru.marslab.pocketwordtranslator.presentation.theme.appBackground

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PocketWordTranslatorTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .appBackground()
                ) {
                    Navigator(screen = rememberScreen(provider = NavGraph.HomeDestination))
                }
            }
        }
    }
}
