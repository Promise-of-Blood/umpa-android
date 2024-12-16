package com.pob.umpa.ui.view.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pob.umpa.ui.view.main.chatting.ChatListScreen
import com.pob.umpa.ui.view.main.community.CommunityScreen
import com.pob.umpa.ui.view.main.community.Question.detail.QuestionDetailScreen
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
        composable("community/question/detail") {
            // 커뮤니티 부분 스크린
            QuestionDetailScreen(modifier = modifier)
        }
        composable("chatting") {
            // 채팅 부분 스크린
            ChatListScreen(modifier = modifier, scaffoldNavController = scaffoldNavController)
        }
        composable("myinfo") {
            // 내 정보 부분 스크린
            MyPageScreen(mainNavController)
        }
        composable("editProfile") {
            //프로필 수정 스크린
            EditProfileScreen()
        }
        composable("lessonProfileScreen") {
            //레슨 프로필 등록 스크린
            EditLessonProfileScreen()
        }
        composable("accompanistProfileScreen"){
            //반주자 프로필 등록 스크린
            AccompanistProfileScreen()
        }
        composable("scoreMakingProfileScreen"){
            //악보 제작 프로필 등록 스크린
            ScoreMakingProfileScreen()
        }
        composable("mrMakingProfileScreen"){
            //엠알 제작 프로필 등록 스크린
            MrMakingProfileScreen()
        }
        
        composable("managementServiceScreen"){
            //서비스 관리 스크린
            ManagementServiceScreen(navController = mainNavController)
        }
        composable("calendar") {
            // 매칭 서비스 부분 스크린
            CalendarScreen(modifier = modifier)
        }
    }
}
