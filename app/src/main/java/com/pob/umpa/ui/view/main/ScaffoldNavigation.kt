package com.pob.umpa.ui.view.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pob.umpa.ui.view.main.chatting.ChattingScaffoldScreen
import com.pob.umpa.ui.view.main.home.calendar.CalendarSchoolScreen

@Composable
fun ScaffoldNavigation(
    scaffoldNavController : NavHostController,
) {
    val mainNavController = rememberNavController()

    NavHost(navController = scaffoldNavController, startDestination = "main") {
        composable ("main") {
            MainScaffold(mainNavController = mainNavController, scaffoldNavController = scaffoldNavController)
        }
        composable ("calendarSchool") {
            CalendarSchoolScreen(scaffoldNavController)
        }
        composable ("chatting") {
            ChattingScaffoldScreen(scaffoldNavController)
        }
    }
}