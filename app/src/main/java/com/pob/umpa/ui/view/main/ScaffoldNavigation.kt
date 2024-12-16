package com.pob.umpa.ui.view.main

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pob.umpa.ui.view.main.chatting.ChattingScaffoldScreen
import com.pob.umpa.ui.view.main.home.calendar.CalendarSchoolScreen

@Composable
fun ScaffoldNavigation(
    modifier: Modifier = Modifier,
    scaffoldNavController : NavHostController,
) {
    val mainNavController = rememberNavController()

    NavHost(navController = scaffoldNavController, startDestination = "main") {
        composable ("main") {
            MainScaffold(mainNavController = mainNavController, scaffoldNavController = scaffoldNavController)
        }
        composable ("calendarSchool") {
            CalendarSchoolScreen(scaffoldNavController = scaffoldNavController)
        }
        composable ("chatting") {
            ChattingScaffoldScreen(scaffoldNavController = scaffoldNavController)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MainScaffoldPreview() {
    MainScaffold(mainNavController = rememberNavController(), scaffoldNavController = rememberNavController())
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CalendarScaffoldPreview() {
    CalendarSchoolScreen(scaffoldNavController = rememberNavController())
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ChattingScaffoldPreview() {
    ChattingScaffoldScreen(scaffoldNavController = rememberNavController())
}