package ru.marslab.pocketwordtranslator.presentation.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import org.koin.androidx.compose.getViewModel
import ru.marslab.pocketwordtranslator.presentation.theme.PocketWordTranslatorTheme
import ru.marslab.pocketwordtranslator.presentation.viewmodels.HistoryViewModel
import ru.marslab.pocketwordtranslator.presentation.viewmodels.SoundViewModel

internal object Destinations {
    const val Translator = "Translator"
    const val History = "History"
}

private const val WORD = "word"

@ExperimentalMaterialNavigationApi
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    PocketWordTranslatorTheme {
        NavHost(navController = navController, startDestination = Destinations.History) {
            composable(
                "${Destinations.Translator}?$WORD={$WORD}",
                arguments = listOf(navArgument(WORD) {
                    nullable = true
                    type = NavType.StringType
                })
            ) {
                WordTranslationScreen(it.arguments?.getString(WORD))
            }
            composable(Destinations.History) {
                HistoryScreen() {
                    navController.navigate("${Destinations.Translator}?$WORD=${it.word}")
                }
            }
        }
    }
}

