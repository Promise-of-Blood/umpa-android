package com.pob.umpa.ui.view.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pob.umpa.ui.view.main.chatting.ChatListScreen
import com.pob.umpa.ui.view.main.community.CommunityScreen
import com.pob.umpa.ui.view.main.home.HomeScreen
import com.pob.umpa.ui.view.main.home.calendar.CalendarScreen
import com.pob.umpa.ui.view.main.matching.MatchingScreen
import com.pob.umpa.ui.view.main.matching.detail.MatchingDetailScreen

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    mainNavController : NavHostController,
    scaffoldNavController : NavHostController,
) {
    NavHost(navController = mainNavController, startDestination = "home") {
        composable("home") {
            // 홈 부분 스크린
            HomeScreen(modifier = modifier, scaffoldNavController = scaffoldNavController, mainNavController = mainNavController)
        }
        composable("contact") {
            // 매칭 서비스 부분 스크린
            MatchingScreen(mainNavController, modifier)
        }
        composable("contact/{lessonId}") { backStackEntry ->
            // 매칭 서비스 상세 부분 스크린
            MatchingDetailScreen(
                lessonId = backStackEntry.arguments?.getString("lessonId") ?: "",
            )
        }
        composable("community") {
            // 커뮤니티 부분 스크린
            CommunityScreen()
        }
        composable("chatting") {
            // 채팅 부분 스크린
            ChatListScreen(modifier = modifier, scaffoldNavController = scaffoldNavController)
        }
        composable("myinfo") {
            // 내 정보 부분 스크린
            MyPageScreen()
        }
        composable("calendar") {
            // 매칭 서비스 부분 스크린
            CalendarScreen(modifier = modifier)
        }
    }
}
