package com.pob.umpa.ui.view.main.community

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pob.umpa.ui.view.main.community.AcceptReview.AcceptReviewDetailScreen
import com.pob.umpa.ui.view.main.community.AcceptReview.AcceptReviewScreen
import com.pob.umpa.ui.view.main.community.InformationSharing.InformationSharingScreen
import com.pob.umpa.ui.view.main.community.Question.QuestionsScreen
import com.pob.umpa.ui.view.main.community.mentoring.MentoringDetailScreen
import com.pob.umpa.ui.view.main.community.mentoring.MentoringScreen

@Composable
fun CommunityNavigation(
    navController: NavHostController, modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController, startDestination = "accept_review", modifier = modifier
    ) {
        composable("accept_review") {
            AcceptReviewScreen()
        }
        composable("information_sharing") {
            InformationSharingScreen()
        }
        composable("questions") {
            QuestionsScreen()
        }
        composable("mentoring") {
            MentoringScreen(
                navController = navController,
            )
        }
        composable(
            route = "mentoring/{mentoringId}",
            arguments = listOf(navArgument("mentoringId") { type = NavType.StringType })
        ) { backStackEntry ->
            MentoringDetailScreen(
                mentoringId = backStackEntry.arguments?.getString("mentoringId") ?: "",
            )
        }
        composable(
            route = "accept_review_detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            AcceptReviewDetailScreen(acceptDetailId = id)
        }
    }
}
