package ru.marslab.pocketwordtranslator.presentation.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import ru.marslab.pocketwordtranslator.R
import ru.marslab.pocketwordtranslator.presentation.theme.PocketWordTranslatorTheme

sealed class Destinations(
    val route: String,
    val label: String,
    @DrawableRes val icon: Int
) {
    object Translator :
        Destinations(
            route = "Translator",
            label = "Translator",
            icon = R.drawable.ic_baseline_translate_24
        )

    object History :
        Destinations(
            route = "History",
            label = "History",
            icon = R.drawable.ic_baseline_history_24
        )
}

internal val screens = listOf(
    Destinations.Translator,
    Destinations.History
)

private const val WORD = "word"

@ExperimentalMaterialNavigationApi
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    PocketWordTranslatorTheme {
        Scaffold(
            bottomBar = {
                MainBottomBar(navController)
            }
        ) { innerPadding ->
            MainNavHost(navController, Modifier.padding(innerPadding))
        }
    }
}

@Composable
private fun MainBottomBar(navController: NavHostController) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        screens.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = null
                    )
                },
                label = { screen.label },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route)
                })
        }
    }
}

@Composable
private fun MainNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Destinations.History.route,
        modifier = modifier
    ) {
        composable(
            "${Destinations.Translator.route}?$WORD={$WORD}",
            arguments = listOf(navArgument(WORD) {
                nullable = true
                type = NavType.StringType
            })
        ) {
            WordTranslationScreen(it.arguments?.getString(WORD))
        }
        composable(Destinations.History.route) {
            HistoryScreen() {
                navController.navigate("${Destinations.Translator.route}?$WORD=${it.word}")
            }
        }
    }
}

