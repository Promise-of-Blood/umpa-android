package com.pob.umpa.ui.view.main.home.calendar

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
fun CalendarSchoolScreen(
    modifier: Modifier = Modifier,
    scaffoldNavController: NavHostController
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "학교 관리",
                        fontFamily = pretendardFontFamily,
                        fontWeight = FontWeight.Black, fontSize = 24.sp,
                        modifier = Modifier.padding(end = 12.dp)
                    ) },
                backgroundColor = Color.White,
                contentColor = Color.Black,
                modifier = Modifier.height(80.dp),
                elevation = 0.dp
            )
        },
    ) { innerPadding ->
        Column {
            SchoolDivider()
            SchoolClosedItem(Modifier.padding(innerPadding))
            SchoolDivider()
            SchoolOpenItem(Modifier.padding(innerPadding))
            Spacer(modifier = Modifier.padding(8.dp))
            SchoolDivider()
            Spacer(modifier = Modifier.padding(8.dp))
            AddSchoolButton(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.weight(1f))
            SaveSchoolButton(modifier = Modifier)
        }
    }
}

@Composable
fun SchoolItem(modifier: Modifier = Modifier, isClosed: Boolean) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("서울예술대학교")
        Icon(
            painter = painterResource
                (id =
                if(isClosed)
                    R.drawable.baseline_arrow_drop_down_24
                else
                    R.drawable.baseline_arrow_drop_up_24
                        ),
            contentDescription = null,
            tint = UmpaColor.Grey
        )
    }
}

@Composable
fun SchoolClosedItem(modifier: Modifier = Modifier,) {
    SchoolItem(modifier = modifier, isClosed = true)
}

@Composable
fun SchoolOpenItem(modifier: Modifier = Modifier,) {
    Column {
        SchoolItem(modifier = modifier, isClosed = false)
        SchoolTypeButtonGroup()
        Spacer(modifier = Modifier.padding(4.dp))
        SchoolCheckBoxGroup()
    }
}

@Composable
fun SchoolTypeButtonGroup(modifier: Modifier = Modifier,) {
    Row(
        modifier = modifier.padding(horizontal = 24.dp)
    ) {
        SchoolTypeButton(typeText = "수시")
        Spacer(modifier = Modifier.padding(4.dp))
        SchoolTypeButton(typeText = "정시")
    }
}

@Composable
fun SchoolTypeButton(modifier: Modifier = Modifier, typeText: String) {
    Box (
        modifier = Modifier
            .background(UmpaColor.LightGray, shape = RoundedCornerShape(32.dp))
            .clip(RoundedCornerShape(32.dp))
            .padding(horizontal = 20.dp, vertical = 4.dp)
    ) {
        Text(text = typeText)
    }
}

@Composable
fun SchoolCheckBox(modifier: Modifier = Modifier, schoolText: String, isEndItem: Boolean = false) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = false, onCheckedChange = {})
        Text(text = schoolText)
        if(isEndItem) Spacer(modifier = Modifier.padding(6.dp))
    }
}

@Composable
fun SchoolCheckBoxGroup(modifier: Modifier = Modifier,) {
    Row (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        SchoolCheckBox(schoolText = "원서 접수")
        SchoolCheckBox(schoolText = "실기 일정", isEndItem = true)
    }
}

@Composable
fun SchoolDivider(modifier: Modifier = Modifier,) {
    Spacer(modifier = modifier
        .fillMaxWidth()
        .height(2.dp)
        .background(UmpaColor.LightGray))
}

@Composable
fun AddSchoolButton(modifier: Modifier = Modifier,) {
    Icon(
        painter = painterResource(id = R.drawable.baseline_add_circle_24),
        contentDescription = null,
        tint = UmpaColor.Main,
        modifier = modifier
    )
}

@Composable
fun SaveSchoolButton(modifier: Modifier = Modifier,) {
    Button(
        onClick = {},
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp), colors = ButtonDefaults.buttonColors(
                contentColor = UmpaColor.White,
                containerColor = UmpaColor.Main
            ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = "저장",
            color = UmpaColor.White,
            fontFamily = pretendardFontFamily,
            fontWeight = FontWeight.Bold, fontSize = 20.sp
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun CalendarSchoolScreenPreview() {
    SchoolCheckBoxGroup()
}

