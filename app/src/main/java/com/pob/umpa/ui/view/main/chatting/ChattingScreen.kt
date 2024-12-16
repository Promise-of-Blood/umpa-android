package com.pob.umpa.ui.view.main.chatting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.pob.umpa.R
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.theme.pretendardFontFamily
import com.pob.umpa.ui.view.main.ScaffoldNavItem
import com.pob.umpa.ui.view.main.ScaffoldNavItemList

@Composable
fun ChattingScaffoldScreen(
    modifier: Modifier = Modifier,
    scaffoldNavController: NavHostController
) {
    Scaffold (
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        topBar = {
            TopAppBar(
                title = {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Column (
                            modifier = Modifier.padding(horizontal = 24.dp)
                        ) {
                            Row {
                                Text(
                                    text = "장우영 선생님",
                                    fontFamily = pretendardFontFamily,
                                    fontWeight = FontWeight.Black, fontSize = 16.sp,
                                    modifier = Modifier.padding(end = 12.dp)
                                )
                                Text(text = "베이스·서울/연남동", fontSize = 11.sp, color = Color.Gray)
                            }
                            Row {
                                Text(text = "120,000원", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                Text(text = " /시간", fontSize = 13.sp,)
                            }
                        }
                    }
                        },
                backgroundColor = Color.White,
                contentColor = Color.Black,
                modifier = Modifier.height(80.dp),
                elevation = 5.dp,
                navigationIcon = {
                    Spacer(modifier = Modifier.padding(start = 12.dp))
                    Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                        .clickable { scaffoldNavController.popBackStack() }
                ) }
            )
        },
    ) { innerPadding ->
        ChattingScreen(modifier = Modifier
            .fillMaxSize()
            .background(UmpaColor.LightBlue)
            .padding(innerPadding)
            .padding(horizontal = 12.dp))
    }
}

@Composable
fun ChattingScreen(modifier: Modifier = Modifier,) {
    Box(
        modifier = modifier
    ) {
        LazyColumn (
            modifier
        ) {
            item {
                DateDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .align(Alignment.Center))
            }
            items(10) {
                MyChattingItem(
                    text = "안녕하세요 저 베이스",
                    time = "오후 7시 54분",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .align(Alignment.Center)
                )
                OtherChattingItem(
                    text = "안녕하세요!",
                    time = "오후 7시 54분",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun DateDivider(modifier: Modifier = Modifier,) {
    Text(
        text = "2024년 11월 9일 토요일",
        modifier = modifier,
        textAlign = TextAlign.Center,
        fontSize = 13.sp,
        color = Color.Gray
    )
}

@Composable
fun MyChattingItem(modifier: Modifier = Modifier, text: String, time: String) {
    Row (
        modifier,
        horizontalArrangement = Arrangement.End
    ) {
        ChatTimestamp(modifier = Modifier.align(Alignment.Bottom), time = time)
        Spacer(modifier = Modifier.padding(4.dp))
        ChatBubble(text = text)
    }
}

@Composable
fun OtherChattingItem(modifier: Modifier = Modifier, text: String, time: String) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(id = R.drawable.banner_t1),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Row (
            modifier = Modifier.align(Alignment.Bottom)
        ) {
            ChatBubble(text = text)
            Spacer(modifier = Modifier.padding(4.dp))
            ChatTimestamp(modifier = Modifier.align(Alignment.Bottom), time = time)
        }
    }

}

@Composable
fun ChatBubble(modifier: Modifier = Modifier, text: String) {
    Box (
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(UmpaColor.White)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = text)
    }
}

@Composable
fun ChatTimestamp(modifier: Modifier = Modifier, time: String) {
    Text(text = time, fontSize = 10.sp, color = Color.Gray, modifier = modifier)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChattingScreenPreview() {
}