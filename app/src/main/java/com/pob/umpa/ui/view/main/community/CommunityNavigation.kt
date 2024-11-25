package com.pob.umpa.ui.view.main.community

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pob.umpa.ui.view.main.Greeting

@Composable
fun CommunityNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = "accept_review", modifier = modifier) {
        composable("accept_review") {
            AcceptReview()
        }
        composable("information_sharing") {
            InformationSharingScreen()
        }
        composable("questions") {
            QuestionsScreen()
        }
        composable("mentoring") {
            Greeting(name = "멘토링")
        }
    }
}
