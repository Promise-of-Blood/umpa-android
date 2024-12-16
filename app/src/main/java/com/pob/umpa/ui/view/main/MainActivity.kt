package com.pob.umpa.ui.view.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
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
import androidx.navigation.NavHostController
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
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
    MainScaffoldComponent(
        mainNavController = mainNavController,
        scaffoldNavController = scaffoldNavController,
        backStackEntry = backStackEntry.value
    )
}

@Composable
fun MainScaffoldComponent(
    modifier: Modifier = Modifier,
    mainNavController: NavHostController,
    scaffoldNavController: NavHostController,
    backStackEntry: NavBackStackEntry?,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        topBar = {
            // TODO TopBar를 if문으로 나누지 않고, 같은 컴포넌트 안에서, 데이터 클래스의 속성에 따라 분류하는 방식으로 변경하는 게 좋을 것 같음
            if(backStackEntry?.destination?.route == "calendar") {
                CalendarTopAppBar(scaffoldNavController = scaffoldNavController)
            }
            else {
                MainNavTopAppBar(mainNavController = mainNavController)
            }
        },
        bottomBar = {
            MainBottomNavigation(backStackEntry = backStackEntry, mainNavController = mainNavController)
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
fun MainBottomNavigation(modifier: Modifier = Modifier, backStackEntry: NavBackStackEntry?, mainNavController: NavHostController) {
    val isMainNavigation = MainItemList.any { it.route == backStackEntry?.destination?.route }
    if (isMainNavigation) {
        BottomNavigation (
            backgroundColor = UmpaColor.White,
            contentColor = UmpaColor.Black,
            elevation = 10.dp,
        ) {
            MainItemList.forEach { item ->
                BottomNavigationItem(
                    selected = item.route == backStackEntry?.destination?.route,
                    onClick = { mainNavController.navigate(item.route) },
                    label = { Text(text = item.name, style = TextStyle(fontSize = 12.sp)) },
                    icon = { Icon(painter = painterResource(id = item.icon), contentDescription = item.name) },
                    selectedContentColor = Black,
                    unselectedContentColor = UmpaColor.MiddleGrey
                )
            }
        }
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
fun MainNavTopAppBar(modifier: Modifier = Modifier, mainNavController: NavHostController) {
    val currentRoute = mainNavController.currentBackStackEntry?.destination?.route
    val isMainNavigation = MainItemList.any { it.route == currentRoute }
    TopAppBar(
        title = {
            Text(
                text = MainItemList.find { it.route == currentRoute }?.topTitle ?: "",
                fontFamily = pretendardFontFamily,
                fontWeight = FontWeight.Black,
                fontSize = 24.sp,
                modifier = Modifier.padding(horizontal = 12.dp
                )
            ) },
        backgroundColor = UmpaColor.White,
        contentColor = UmpaColor.Black,
        modifier = Modifier.height(60.dp),
        elevation = 0.dp,
        navigationIcon = if (!isMainNavigation) {
            {
                IconButton(onClick = { mainNavController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "뒤로가기"
                    )
                }
            }
        } else null,
        actions = {
            val serviceAddPageList = listOf( "lessonProfileScreen","accompanistProfileScreen", "scoreMakingProfileScreen" , "mrMakingProfileScreen","editProfile")
            if(currentRoute in serviceAddPageList){

                Text(
                    text = "완료",
                    style = TextStyle(color = UmpaColor.Main, fontSize = 16.sp, fontFamily = pretendardFontFamily, fontWeight = FontWeight.SemiBold),
                    modifier = Modifier
                        .clickable { mainNavController.popBackStack() }
                        .padding(end = 16.dp)
                )

            }
        }
    )
}

@Composable
fun ErrorScaffoldComponent(modifier : Modifier = Modifier) {
    Scaffold { innerPadding ->
        Text(text = "오류 발생 ! ! !", modifier = modifier
            .padding(innerPadding)
            .fillMaxSize())
    }
}