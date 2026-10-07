package com.example.getar.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.getar.ui.screens.DetailScreen
import com.example.getar.ui.screens.HomeScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    viewModel: GempaViewModel = viewModel()
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable(route = "home") {
            HomeScreen(
                viewModel = viewModel,
                onGempaClick = { index ->
                    navController.navigate("detail/$index")
                }
            )
        }

        composable(
            route = "detail/{index}",
            arguments = listOf(
                navArgument("index") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt("index") ?: 0
            DetailScreen(
                gempaIndex = index,
                viewModel = viewModel,
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}
