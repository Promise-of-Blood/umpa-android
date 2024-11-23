package com.pob.umpa.ui.view.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pob.umpa.ui.view.main.chatting.ChatListScreen
import com.pob.umpa.ui.view.main.home.HomeScreen

@Composable
fun MainNavigation(
    navController : NavHostController,
    modifier: Modifier,
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            // 홈 부분 스크린
            HomeScreen(modifier)
        }
        composable("contact") {
            // 매칭 서비스 부분 스크린
            Greeting(name = "매칭 서비스")
        }
        composable("community") {
            // 커뮤니티 부분 스크린
            Greeting(name = "커뮤니티")
        }
        composable("chatting") {
            // 채팅 부분 스크린
            ChatListScreen(modifier)
        }
        composable("myinfo") {
            // 내 정보 부분 스크린
            Greeting(name = "내 정보")
        }
    }
}