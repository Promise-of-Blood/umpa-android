package com.pob.umpa.ui.view.main.community

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pob.umpa.ui.theme.UmpaColor


@Composable
fun CommunityScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val selectedIndex = remember { mutableStateOf(0) }
    val tabs = listOf(
        "합격후기" to "accept_review",
        "정보 공유" to "information_sharing",
        "질문" to "questions",
        "멘토링" to "mentoring"
    )

    Column(modifier = modifier.fillMaxSize()) {
        val backStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry.value?.destination?.route
        selectedIndex.value = tabs.indexOfFirst { it.second == currentRoute }.takeIf { it != -1 } ?: 0

        TabRow(
            selectedTabIndex = selectedIndex.value,
            containerColor = UmpaColor.White,
            contentColor = UmpaColor.Black,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedIndex.value]),
                    color = UmpaColor.Main
                )
            }
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedIndex.value == index,
                    onClick = {
                        if (currentRoute != tab.second) {
                            selectedIndex.value = index
                            navController.navigate(tab.second)
                        }
                    },
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(vertical = 16.dp),
                ) {
                    Text(
                        text = tab.first,
                        maxLines = 1, // 한 줄로 제한
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }
        CommunityNavigation(navController = navController, modifier = Modifier.fillMaxSize())
    }
}