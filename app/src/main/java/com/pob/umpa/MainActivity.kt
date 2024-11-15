package com.pob.umpa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pob.umpa.ui.theme.UmpaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val items = listOf(
                MainNavItem(
                    name = "홈",
                    route = "home",
                    icon = R.drawable.baseline_home_24
                ),
                MainNavItem(
                    name = "매칭서비스",
                    route = "contact",
                    icon = R.drawable.baseline_matching_24
                ),
                MainNavItem(
                    name = "커뮤니티",
                    route = "community",
                    icon = R.drawable.baseline_community_24
                ),
                MainNavItem(
                    name = "채팅",
                    route = "chatting",
                    icon = R.drawable.baseline_chat_24
                ),
                MainNavItem(
                    name = "내 정보",
                    route = "myinfo",
                    icon = R.drawable.baseline_myinfo_24
                ),
            )
            UmpaTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        val backStackEntry = navController.currentBackStackEntryAsState()
                        BottomNavigation (
                            backgroundColor = Color.White,
                            contentColor = Color.Black,
                            elevation = 5.dp,
                        ){
                            items.forEach { item ->
                                BottomNavigationItem(
                                    selected = item.route == backStackEntry.value?.destination?.route,
                                    onClick = { navController.navigate(item.route) },
                                    label = { Text(text = item.name, fontSize = 12.sp) },
                                    icon = { Icon(painter = painterResource(id = item.icon), contentDescription = item.name) },
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    Navigation(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),

                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier.padding(20.dp)
    )
}

@Composable
fun Navigation(
    navController : NavHostController,
    modifier: Modifier,
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            // 홈 부분 스크린
            Greeting(name = "홈")
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
            Greeting(name = "채팅")
        }
        composable("myinfo") {
            // 내 정보 부분 스크린
            Greeting(name = "내 정보")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UmpaTheme {
        Greeting("Android")
    }
}