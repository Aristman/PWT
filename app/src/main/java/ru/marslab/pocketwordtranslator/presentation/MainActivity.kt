package ru.marslab.pocketwordtranslator.presentation

import android.animation.ObjectAnimator
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import ru.marslab.pocketwordtranslator.R.style
import ru.marslab.pocketwordtranslator.presentation.screens.MainScreen
import ru.marslab.pocketwordtranslator.presentation.theme.PocketWordTranslatorTheme

private const val ANIMATE_DURATION = 2000L

@ExperimentalMaterialNavigationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        showNewSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            PocketWordTranslatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }

    private fun showNewSplashScreen() {
        if (VERSION.SDK_INT >= VERSION_CODES.S) {
            splashScreen.setSplashScreenTheme(style.SplashTheme)
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                ObjectAnimator.ofFloat(
                    splashScreenView,
                    View.TRANSLATION_Y,
                    0f,
                    splashScreenView.height.toFloat()
                )
                    .apply {
                        interpolator = AnticipateInterpolator()
                        duration = ANIMATE_DURATION
                        doOnEnd {
                            splashScreenView.remove()
                        }
                    }
                    .start()
            }
        }
    }
}
