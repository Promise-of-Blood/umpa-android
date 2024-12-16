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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pob.umpa.R
import com.pob.umpa.ui.theme.UmpaColor

@Composable
fun ChatListScreen(modifier: Modifier, scaffoldNavController : NavHostController,) {
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn (
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items (2) {
                ChatListItem(scaffoldNavController)
            }
        }
    }
}

@Composable
fun ChatListItem(scaffoldNavController : NavHostController,) {
    Column (
        Modifier.clickable { scaffoldNavController.navigate("chatting") }
    ) {
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.banner_t1),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column (
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Row {
                    Text(text = "장우영 선생님", fontWeight = FontWeight.Bold, fontSize = 15.sp, modifier = Modifier)
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = "베이스·서울/연남동", fontSize = 11.sp, color = Color.Gray)
                }
                Row {
                    Text(text = "120,000원", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    Text(text = " /시간", fontSize = 13.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "2024.11.09", fontSize = 11.sp, color = Color.Gray)
                }
            }
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Text(text = "네 그럼 ~~", fontSize = 11.sp, color = Color.Gray)
        Spacer(modifier = Modifier.padding(6.dp))
        Divider(color = UmpaColor.LightGray, thickness = 2.dp)
    }
}