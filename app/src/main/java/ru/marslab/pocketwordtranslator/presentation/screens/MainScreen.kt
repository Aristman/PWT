package ru.marslab.pocketwordtranslator.presentation.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import ru.marslab.pocketwordtranslator.presentation.theme.PocketWordTranslatorTheme
import ru.marslab.pocketwordtranslator.presentation.viewmodels.HistoryViewModel
import ru.marslab.pocketwordtranslator.presentation.viewmodels.HistoryViewModelImpl
import ru.marslab.pocketwordtranslator.presentation.viewmodels.SoundViewModelImpl
import ru.marslab.pocketwordtranslator.presentation.viewmodels.TranslationViewModelImpl

internal object Destinations {
    const val Translator = "Translator"
    const val History = "History"
}

@ExperimentalMaterialNavigationApi
@Composable
fun MainScreen() {
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberNavController(bottomSheetNavigator)
    val translationViewModel = viewModel(modelClass = TranslationViewModelImpl::class.java)
    val soundViewModel = viewModel(modelClass = SoundViewModelImpl::class.java)
    val historyViewModel = viewModel(modelClass = HistoryViewModelImpl::class.java)
    PocketWordTranslatorTheme {
        ModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator) {
            NavHost(navController = navController, startDestination = Destinations.History) {
                composable(Destinations.Translator) {
                    WordTranslationScreen(translationViewModel, soundViewModel)
                }
                composable(Destinations.History) {
                    HistoryScreen(historyViewModel = historyViewModel) {
                        navController.navigate(Destinations.Translator)
                    }
                }
            }
        }
    }
}

