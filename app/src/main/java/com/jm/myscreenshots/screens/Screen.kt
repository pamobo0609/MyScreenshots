package com.jm.myscreenshots.screens

sealed class Screen (val route: String) {
    data object Login : Screen("login")
    data object Details : Screen("details")
}