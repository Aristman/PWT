package ru.marslab.pocketwordtranslator.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator

internal object Destinations {
    const val Translator = "Translator"
}

@ExperimentalMaterialNavigationApi
@Composable
fun MainScreen() {
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberNavController(bottomSheetNavigator)
    val mainViewModel = viewModel(modelClass = MainViewModel::class.java)
    ModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator) {
        NavHost(navController = navController, startDestination = Destinations.Translator) {
            composable(Destinations.Translator) {
                WordTranslationScreen(mainViewModel)
            }
        }
    }
}

