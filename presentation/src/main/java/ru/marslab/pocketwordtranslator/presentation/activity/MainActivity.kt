package ru.marslab.pocketwordtranslator.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import ru.marslab.pocketwordtranslator.presentation.feature.root.RootView
import ru.marslab.pocketwordtranslator.presentation.theme.PocketWordTranslatorTheme

class MainActivity : ComponentActivity(), DIAware {

    override val di by closestDI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PocketWordTranslatorTheme {
                RootView()
            }
        }
    }
}
