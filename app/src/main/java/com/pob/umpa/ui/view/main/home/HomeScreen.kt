package com.pob.umpa.ui.view.main.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pob.umpa.R
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.theme.pretendardFontFamily
import com.pob.umpa.ui.view.main.ScaffoldNavItem
import com.pob.umpa.ui.view.main.ScaffoldNavItemList
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(modifier: Modifier = Modifier, scaffoldNavController : NavHostController, mainNavController: NavHostController) {
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            item {
                HomeItemTitle(modifier = Modifier, title = "선생님 찾기")
                HomeFindTeacher(modifier = Modifier, mainNavController = mainNavController)
                Spacer(modifier = Modifier.padding(16.dp))
                HomeBanner()
                Spacer(modifier = Modifier.padding(16.dp))
                HomeItemTitle(modifier = Modifier, title = "음파 커뮤니티")
                Spacer(modifier = Modifier.padding(6.dp))
                HomeCommunityShortcut(modifier = modifier, mainNavController = mainNavController)
                Spacer(modifier = Modifier.padding(16.dp))
                HomeItemTitle(modifier = Modifier, title = "입시 캘린더")
                Spacer(modifier = Modifier.padding(6.dp))
                HomeCalendar(mainNavController = mainNavController)
                Spacer(modifier = Modifier.padding(16.dp))
            }
        }

    }
}

@Composable
fun HomeItemTitle(modifier: Modifier = Modifier, title: String) {
    Text(
        modifier = modifier.padding(start = 8.dp),
        text = title,
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Black,
        fontSize = 18.sp
    )
}

@Composable
fun HomeFindTeacher(modifier: Modifier = Modifier, mainNavController: NavHostController) {
    val pagerState = rememberPagerState(pageCount = {2})
    HorizontalPager(state = pagerState) { page ->
        Column(modifier = modifier) {
            for(i in 0..< 2) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for(j in 0..4) {
                        HomeFindTeacherItem(
                            item =
                            if (TeacherSubjectList.size > (i * 5 + j) + page * 10) TeacherSubjectList[(i * 5 + j) + page * 10]
                            else EmptyTeacherSubject,
                            mainNavController = mainNavController
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomeFindTeacherItem(modifier: Modifier = Modifier, item : TeacherSubject, mainNavController: NavHostController) {
    if(item.type == "empty") {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            Spacer(modifier = Modifier.size(50.dp))
            Text(text = item.name, modifier = Modifier.widthIn(min = 10.dp, max = 60.dp), fontSize = 12.sp)
        }
    }
    else {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .clickable { mainNavController.navigate("contact") }
        ) {
            Card(
                modifier = Modifier
                    .size(50.dp)
                    .padding(vertical = 2.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardColors(UmpaColor.LightBlue, UmpaColor.LightBlue, UmpaColor.LightBlue, UmpaColor.LightBlue)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = item.image),
                        contentDescription = item.name,
                        contentScale = ContentScale.Fit,
                    )
                }
            }
            Text(text = item.name, modifier = Modifier.widthIn(min = 10.dp, max = 60.dp), fontSize = 12.sp)
        }
    }
}

val bannerList = listOf(
    R.drawable.banner_t1,
    R.drawable.banner_arcane,
    R.drawable.banner_flip6
)

// 배너

@Composable
fun HomeBanner(modifier: Modifier = Modifier,) {
    val pagerState = rememberPagerState (pageCount = {
        3
    })
    LaunchedEffect(key1 = Unit) {
        while(true) {
            for(i in 0..2) {
                delay(5000)
                pagerState.animateScrollToPage(i)
            }
        }
    }
    Box {
        HorizontalPager(state = pagerState) { page ->
            HomeBannerItem(imgId = bannerList[page])
        }
        HomeBannerPage(
            text = (pagerState.currentPage + 1).toString(),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 12.dp, bottom = 6.dp)
        )
    }
}

@Composable
fun HomeBannerItem(modifier: Modifier = Modifier, imgId : Int) {
    Image(
        painter = painterResource(id = imgId),
        contentDescription = "배너",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
fun HomeBannerPage(modifier: Modifier = Modifier, text: String) {
    Box(modifier = modifier
        .width(60.dp)
        .height(25.dp)
        .background(color = UmpaColor.Opacity50Black, shape = RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    )  {
        Text(
            "$text/3", color = UmpaColor.White,
        )
    }
}

@Composable
fun HomeCommunityShortcut(modifier: Modifier = Modifier, mainNavController: NavHostController) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(
                horizontal = 12.dp
            )
            .fillMaxWidth()
    ) {
        CommunityShortcutItemList.forEach {
            HomeCommunityShortcutItem(item = it, mainNavController = mainNavController)
        }
    }
}

@Composable
fun HomeCommunityShortcutItem(modifier: Modifier = Modifier, item: CommunityShortcutItem, mainNavController: NavHostController) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { mainNavController.navigate("community") }
    ) {
        Image(painter = painterResource(id = item.image), contentDescription = item.name, modifier = Modifier.size(40.dp))
        Spacer(modifier = Modifier.padding(2.dp))
        Text(text = item.name, textAlign = TextAlign.Center, fontSize = 12.sp)
    }
}

//
@Composable
fun HomeCalendar(modifier: Modifier = Modifier, mainNavController : NavHostController) {
    Log.d("달력 날짜 표시", "${getNowMonth()}, ${getFirstDay()}, ${getLastDay()}")
    // 30 * 6 (박스) + 2 * 5 (선) + 10(패딩)
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(2.dp, UmpaColor.LightGray, shape = RoundedCornerShape(10.dp))
            .background(UmpaColor.LightGray)
            .clickable { mainNavController.navigate("calendar") }
        ,
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(7), verticalArrangement = Arrangement.Center) {
            // 맨 위 7개 고정
            // 첫 날짜 요일 값만큼 빈 거 추가 후,
            // 1~마지막 날짜 까지 한 후,
            // 마지막 날짜 요일 구해서, 7 - 1 - 값 을 빈 거 추가
            items(7) {
                Box (
                    modifier = Modifier
                        .height(30.dp)
                        .padding(1.dp)
                        .background(UmpaColor.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = WeekKorean[it],
                        textAlign = TextAlign.Center,
                        fontSize = 13.sp,
                        fontFamily = pretendardFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            items(getFirstDay() % 7) {
                Log.d("getFirstDay", getFirstDay().toString())
                Spacer(
                    modifier = Modifier
                        .height(30.dp)
                        .padding(1.dp)
                        .background(UmpaColor.White)
                )
            }

            items(getNumOfDays()) {
                Box (
                    modifier = Modifier
                        .height(30.dp)
                        .padding(1.dp)
                        .background(UmpaColor.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${it + 1}",
                        textAlign = TextAlign.Center,
                        fontSize = 11.sp
                    )
                }
            }

            items (7 - 1 - getLastDay()) {
                Spacer(
                    modifier = Modifier
                        .height(30.dp)
                        .padding(1.dp)
                        .background(UmpaColor.White)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeBanner()
}