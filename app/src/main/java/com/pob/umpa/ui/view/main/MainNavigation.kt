package com.pob.umpa.ui.view.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pob.umpa.ui.view.main.community.CommunityScreen
import com.pob.umpa.ui.view.main.home.HomeScreen
import com.pob.umpa.ui.view.main.matching.MatchingScreen
import com.pob.umpa.ui.view.main.matching.detail.MatchingDetailScreen

@Composable
fun MainNavigation(
    navController: NavHostController,
    modifier: Modifier,
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            // 홈 부분 스크린
            HomeScreen(modifier)
        }
        composable("contact") {
            // 매칭 서비스 부분 스크린
            MatchingScreen(navController, modifier)
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
            Greeting(name = "채팅")
        }
        composable("myinfo") {
            // 내 정보 부분 스크린
            MyPageScreen(navController)
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
            ManagementServiceScreen(navController = navController)
        }
    }
}
