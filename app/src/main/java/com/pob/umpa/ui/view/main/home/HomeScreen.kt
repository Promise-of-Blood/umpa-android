package com.pob.umpa.ui.view.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pob.umpa.ui.theme.LightBlue
import com.pob.umpa.ui.theme.Main
import com.pob.umpa.ui.theme.Terri
import com.pob.umpa.ui.theme.pretendardFontFamily

@Composable
fun HomeScreen(modifier: Modifier,) {
    Column {
        HomeItemTitle(modifier = modifier, title = "하이요")
        HomeFindTeacher(modifier = modifier)
    }
}

@Composable
fun HomeItemTitle(modifier: Modifier, title: String) {
    Text(
        modifier = modifier,
        text = title,
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Black,
        fontSize = 24.sp
    )
}

@Composable
fun HomeFindTeacher(modifier: Modifier) {
    Column(modifier = modifier) {
        for(i in 0..< TeacherSubjectList.size / 5) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                for(j in 0..4) {
                    HomeFindTeacherItem(item = TeacherSubjectList[i * 5 + j])
                }
            }
        }
    }
}

@Composable
fun HomeFindTeacherItem(item : TeacherSubject) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        Card(
            modifier = Modifier.widthIn(min = 10.dp, max = 50.dp).padding(horizontal = 4.dp, vertical = 2.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardColors(LightBlue, LightBlue, LightBlue, LightBlue)
        ) {
            Image(
                modifier = Modifier.widthIn(min = 10.dp, max = 50.dp),
                painter = painterResource(id = item.image),
                contentDescription = item.name,
                contentScale = ContentScale.Fit,
            )
        }
        Text(text = item.name, modifier = Modifier.widthIn(min = 10.dp, max = 60.dp))
    }
}

@Composable
fun HomeCommunityShortcut() {

}

@Composable
fun HomeCalendar() {

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeFindTeacher(modifier = Modifier)
}