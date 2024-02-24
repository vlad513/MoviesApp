package com.example.moviesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviesapp.MainViewModel
import com.example.moviesapp.screens.DetailsScreen
import com.example.moviesapp.screens.MainScreen
import com.example.moviesapp.screens.SplashScreen
import com.example.moviesapp.utils.Constant


sealed class Screens(val route: String) {
    object Splash : Screens(route = Constant.Screens.SPLASH_SCREEN)
    object Main : Screens(route = Constant.Screens.MAIN_SCREEN)
    object Details : Screens(route = Constant.Screens.DETAILS_SCREEN)
}

@Composable
fun SetupNavHost(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ) {
        composable(route = Screens.Splash.route) {
            SplashScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.Main.route) {
            MainScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.Details.route + "/{Id}") {backStackEntry ->
            DetailsScreen(
                navController = navController,
                viewModel = viewModel,
                itemId = backStackEntry.arguments?.getString("Id") ?: "1"
            )
        }
    }
}