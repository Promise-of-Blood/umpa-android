package com.pob.umpa.ui.view.main.sign.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pob.umpa.ui.view.main.sign.screen.SetMajorScreen
import com.pob.umpa.ui.view.main.sign.screen.SetNameScreen
import com.pob.umpa.ui.view.main.sign.screen.SetStudentDesiredUniversity
import com.pob.umpa.ui.view.main.sign.screen.SignEndScreen
import com.pob.umpa.ui.view.main.sign.screen.SignInScreen
import com.pob.umpa.ui.view.main.sign.screen.UserTypeSelectionScreen

@Composable
fun SignNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "sign_in") {
        composable("sign_in") { SignInScreen(navController) }
        composable("user_type") { UserTypeSelectionScreen(navController) }
        composable("set_name") { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry("user_type")
            }
            SetNameScreen(navController, viewModel = hiltViewModel(parentEntry))
        }
        composable("set_major") { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry("user_type")
            }
            SetMajorScreen(navController, viewModel = hiltViewModel(parentEntry))
        }
        composable("set_student_university") { SetStudentDesiredUniversity(navController) }
        composable("sign_end") { SignEndScreen(navController) }
    }
}