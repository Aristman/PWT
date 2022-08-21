package ru.marslab.pocketwordtranslator.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.Navigator
import ru.marslab.pocketwordtranslator.navigation.NavGraph
import ru.marslab.pocketwordtranslator.presentation.theme.PocketWordTranslatorTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PocketWordTranslatorTheme {
                Navigator(screen = rememberScreen(provider = NavGraph.HomeDestination))
            }
        }
    }
}
