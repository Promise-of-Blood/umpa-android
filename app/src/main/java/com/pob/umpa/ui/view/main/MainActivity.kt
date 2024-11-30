package com.pob.umpa.ui.view.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.theme.UmpaTheme
import com.pob.umpa.ui.theme.pretendardFontFamily

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            UmpaTheme {
                val backStackEntry = navController.currentBackStackEntryAsState()

                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .safeDrawingPadding(), topBar = {
                    MainTopAppBar(
                        backStackEntry = backStackEntry.value,
                        navController = navController,
                    )
                }, bottomBar = {
                    MainBottomNavigation(
                        backStackEntry = backStackEntry.value,
                        navController = navController,
                    )
                }) { innerPadding ->
                    MainNavigation(
                        navController = navController,
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(
                                horizontal = 24.dp, vertical = 12.dp
                            ),
                    )
                }
            }
        }
    }
}

@Composable
fun MainTopAppBar(backStackEntry: NavBackStackEntry?, navController: NavController) {
    val currentRoute = backStackEntry?.destination?.route
    val isMainNavigation = MainItemList.any { it.route == currentRoute }

    TopAppBar(
        title = {
            Text(
                text = MainItemList.find { it.route == currentRoute }?.name ?: "",
                fontFamily = pretendardFontFamily,
                fontWeight = FontWeight.Black,
                fontSize = 24.sp,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        },
        backgroundColor = Color.White,
        contentColor = Color.Black,
        modifier = Modifier.height(60.dp),
        elevation = 0.dp,
        navigationIcon = if (!isMainNavigation) {
            {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "뒤로가기"
                    )
                }
            }
        } else null,
    )
}

@Composable
fun MainBottomNavigation(backStackEntry: NavBackStackEntry?, navController: NavController) {
    val isMainNavigation = MainItemList.any { it.route == backStackEntry?.destination?.route }

    if (isMainNavigation) {
        BottomNavigation(
            backgroundColor = Color.White,
            contentColor = Color.Black,
            elevation = 10.dp,
        ) {
            MainItemList.forEach { item ->
                BottomNavigationItem(
                    selected = item.route == backStackEntry?.destination?.route,
                    onClick = { navController.navigate(item.route) },
                    label = {
                        Text(
                            text = item.name, style = TextStyle(fontSize = 12.sp)
                        )
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.name
                        )
                    },
                    selectedContentColor = Black,
                    unselectedContentColor = UmpaColor.MiddleGrey
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier.padding(20.dp),
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Black
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UmpaTheme {
        Greeting("Android")
    }
}