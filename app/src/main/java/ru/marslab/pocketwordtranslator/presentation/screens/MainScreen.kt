package ru.marslab.pocketwordtranslator.presentation.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import ru.marslab.pocketwordtranslator.presentation.theme.PocketWordTranslatorTheme
import ru.marslab.pocketwordtranslator.presentation.viewmodels.TranslationViewModelImpl

internal object Destinations {
    const val Translator = "Translator"
}

@ExperimentalMaterialNavigationApi
@Composable
fun MainScreen() {
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberNavController(bottomSheetNavigator)
    val translationViewModel = viewModel(modelClass = TranslationViewModelImpl::class.java)
    PocketWordTranslatorTheme {
        ModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator) {
            NavHost(navController = navController, startDestination = Destinations.Translator) {
                composable(Destinations.Translator) {
                    WordTranslationScreen(translationViewModel)
                }
            }
        }
    }
}

