package com.pob.umpa.ui.view.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pob.umpa.ui.view.main.chatting.ChatListScreen
import com.pob.umpa.ui.view.main.home.HomeScreen
import com.pob.umpa.ui.view.main.home.calendar.CalendarScreen

@Composable
fun MainNavigation(
    navController : NavHostController,
    modifier: Modifier,
    onScaffoldChange: (ScaffoldType) -> Unit
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            // 홈 부분 스크린
            HomeScreen(modifier, onScaffoldChange)
        }
        composable("calendar") {
            // 매칭 서비스 부분 스크린
//            Greeting(name = "매칭 서비스")
            CalendarScreen(modifier)
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
//        composable("calendar") {
//            // 캘린더 부분 스크린
//            CalendarScreen(modifier)
//        }
    }
}