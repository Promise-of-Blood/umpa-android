package com.pob.umpa.ui.view.main.sign

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun SignNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "") {
        composable("sign_in") { SignInScreen(navController) }
        composable("sign_up") { }
    }
}