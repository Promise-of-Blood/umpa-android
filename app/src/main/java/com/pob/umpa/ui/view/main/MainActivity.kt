package com.pob.umpa.ui.view.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pob.umpa.R
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.theme.UmpaTheme
import com.pob.umpa.ui.theme.pretendardFontFamily
import com.pob.umpa.ui.view.main.home.calendar.CalendarScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val scaffoldNavController = rememberNavController()
            UmpaTheme {
                ScaffoldNavigation(scaffoldNavController = scaffoldNavController)
            }
        }
    }
}

@Composable
fun MainScaffold(
    modifier: Modifier = Modifier,
    mainNavController: NavHostController,
    scaffoldNavController: NavHostController
) {
    val backStackEntry = mainNavController.currentBackStackEntryAsState()
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        topBar = {
            MainItemList.forEach { item ->
                if(backStackEntry.value?.destination?.route == "calendar") {
                    CalendarTopAppBar(scaffoldNavController = scaffoldNavController)
                }
                else if(item.route == backStackEntry.value?.destination?.route) {
                    MainNavTopAppBar(item = item)
                }
            }
        },
        bottomBar = {
            BottomNavigation (
                backgroundColor = Color.White,
                contentColor = Color.Black,
                elevation = 10.dp,
            ) {
                MainItemList.forEach { item ->
                    BottomNavigationItem(
                        selected = item.route == backStackEntry.value?.destination?.route,
                        onClick = { mainNavController.navigate(item.route) },
                        label = { Text(text = item.name, style = TextStyle(fontSize = 12.sp)) },
                        icon = { Icon(painter = painterResource(id = item.icon), contentDescription = item.name) },
                        selectedContentColor = Black,
                        unselectedContentColor = UmpaColor.MiddleGrey
                    )
                }
            }
        }
    ) { innerPadding ->
        MainNavigation(
            mainNavController = mainNavController,
            modifier = Modifier
                .fillMaxSize()
                .background(UmpaColor.White)
                .padding(innerPadding)
                .padding(
                    horizontal = 24.dp,
                    vertical = 12.dp
                ),
            scaffoldNavController = scaffoldNavController,
        )
    }
}

@Composable
fun CalendarTopAppBar(modifier: Modifier = Modifier, scaffoldNavController: NavHostController) {
    TopAppBar(
        title = {
            Text(
                text = "입시 캘린더",
                fontFamily = pretendardFontFamily,
                fontWeight = FontWeight.Black,
                fontSize = 24.sp,
                modifier = Modifier.padding(horizontal = 12.dp)
            ) },
        backgroundColor = UmpaColor.White,
        contentColor = UmpaColor.Black,
        modifier = Modifier.height(60.dp),
        elevation = 0.dp,
        actions = {
            IconButton(onClick = { }) {
                Icon(painter = painterResource(id = R.drawable.baseline_school_24), contentDescription = null, modifier = Modifier.clickable { scaffoldNavController.navigate("calendarSchool") })
            }
        }
    )
}

@Composable
fun MainNavTopAppBar(modifier: Modifier = Modifier, item : MainNavItem) {
    TopAppBar(
        title = {
            Text(
                text = item.topTitle,
                fontFamily = pretendardFontFamily,
                fontWeight = FontWeight.Black,
                fontSize = 24.sp,
                modifier = Modifier.padding(horizontal = 12.dp
                )
            ) },
        backgroundColor = UmpaColor.White,
        contentColor = UmpaColor.Black,
        modifier = Modifier.height(60.dp),
        elevation = 0.dp
    )
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    UmpaTheme {
        CalendarScreen(modifier = Modifier)
    }
}