package ru.marslab.pocketwordtranslator.presentation.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import ru.marslab.pocketwordtranslator.R
import ru.marslab.pocketwordtranslator.presentation.theme.PocketWordTranslatorTheme
import ru.marslab.pocketwordtranslator.presentation.translation.WordTranslationScreen

sealed class NavGraph(
    val route: String,
    val label: String,
    @DrawableRes val icon: Int
) {
    object Translator :
        NavGraph(
            route = "Translator",
            label = "Translator",
            icon = R.drawable.ic_baseline_translate_24
        )

    object History :
        NavGraph(
            route = "History",
            label = "History",
            icon = R.drawable.ic_baseline_history_24
        )
}

internal val buttonTabs = listOf(
    NavGraph.Translator,
    NavGraph.History
)

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
        buttonTabs.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = null
                    )
                },
                label = { screen.label },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                modifier = Modifier,
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Yellow,
                onClick = {
                    navController.navigate(screen.route)
                }
            )
        }
    }
}

@Composable
private fun MainNavHost(navController: NavHostController, modifier: Modifier) {
    var searchWord = remember<String?> { null }
    NavHost(
        navController = navController,
        startDestination = NavGraph.Translator.route,
        modifier = modifier
    ) {
        composable(NavGraph.Translator.route) {
            WordTranslationScreen(searchWord)
            searchWord = null
        }
        composable(NavGraph.History.route) {
            HistoryScreen() {
                searchWord = it.word
                navController.navigate(NavGraph.Translator.route)
            }
        }
    }
}
