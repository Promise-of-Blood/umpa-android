package com.pob.umpa.ui.view.main.sign

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun SignNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "sign_in") {
        composable("sign_in") { SignInScreen(navController) }
        composable("user_type") { UserTypeSelectionScreen(navController)}
        composable("set_name") { SetNameScreen(navController)}
        composable("set_major") { SetMajorScreen(navController)}
        composable("sign_end") { SignEndScreen(navController)}
        composable("set_student_nickname") { SetStudentNickname(navController)}
        composable("set_student_major") { SetStudentMajorScreen(navController)}
        composable("set_student_university") { SetStudentDesiredUniversity(navController) }
    }
}