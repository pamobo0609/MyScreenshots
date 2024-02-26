package com.jm.myscreenshots.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jm.myscreenshots.screens.details.DetailsScreen
import com.jm.myscreenshots.screens.login.LoginScreen

@Composable
fun MyScreenshotsTheme(content: @Composable () -> Unit) = MaterialTheme { content() }

@Composable
fun MyScreenshotsApp() {
    MyScreenshotsNavHost(rememberNavController())
}

@Composable
private fun MyScreenshotsNavHost(
    navHostController: NavHostController
) {
    Scaffold { paddingValues ->
        NavHost(
            navController = navHostController,
            startDestination = Screen.Login.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = Screen.Login.route) { LoginScreen(navHostController) }
            composable(route = Screen.Details.route) { DetailsScreen(navHostController) }
        }
    }
}
