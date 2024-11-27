package com.pob.umpa.ui.view.main.home.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pob.umpa.R
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.theme.pretendardFontFamily
import com.pob.umpa.ui.view.main.home.WeekKorean
import com.pob.umpa.ui.view.main.home.getFirstDay
import com.pob.umpa.ui.view.main.home.getLastDay
import com.pob.umpa.ui.view.main.home.getNumOfDays

@Composable
fun CalendarScreen(modifier : Modifier) {
    Box {
        Column (
            modifier
                .padding(end = 8.dp)
                .background(UmpaColor.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CalendarHeader()
            Spacer(modifier = Modifier.padding(8.dp))
            CalendarContent()
        }
    }
}

@Composable
fun CalendarHeader() {
    Row {
        Icon(painter = painterResource(id = R.drawable.baseline_arrow_left_24), contentDescription = "이전달")
        Spacer(modifier = Modifier.padding(horizontal = 28.dp))
        Text(
            text = "11월",
            fontFamily = pretendardFontFamily,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterVertically),
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.padding(horizontal = 32.dp))
        Icon(painter = painterResource(id = R.drawable.baseline_arrow_right_24), contentDescription = "다음달")
    }
}

@Composable
fun CalendarContent() {
    LazyVerticalGrid(columns = GridCells.Fixed(7)) {
        items(7) {
            CalendarWeekItem(it)
        }

        items(getFirstDay()) {
            EmptyDayItem()
        }

        items(getNumOfDays()) {
            CalendarDayItem(it)
        }

        items (7 - 1 - getLastDay()) {
            EmptyDayItem()
        }
    }
}

@Composable
fun CalendarWeekItem(listNum: Int) {
    Column (
        horizontalAlignment = Alignment.End,
    ) {
        Text(
            text = WeekKorean[listNum],
            textAlign = TextAlign.End,
            fontSize = 18.sp,
            fontFamily = pretendardFontFamily,
            fontWeight = FontWeight.Medium,
        )
        Spacer(modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun CalendarDayItem(listNum: Int) {
    Box (
        modifier = Modifier
            .height(100.dp)
            .padding(1.dp)
            .background(UmpaColor.White),
        contentAlignment = Alignment.TopEnd
    ) {
        Text(
            text = "${listNum + 1}",
            textAlign = TextAlign.End,
            fontSize = 14.sp,
            fontFamily = pretendardFontFamily,
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
fun EmptyDayItem() {
    Spacer(
        modifier = Modifier
            .height(30.dp)
            .padding(1.dp)
            .background(UmpaColor.White)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalendarPreview() {
    CalendarScreen(modifier = Modifier)
}