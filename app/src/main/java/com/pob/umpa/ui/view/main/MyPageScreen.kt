package com.pob.umpa.ui.view.main


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pob.umpa.R
import com.pob.umpa.ui.theme.UmpaColor.Companion.Grey
import com.pob.umpa.ui.theme.UmpaColor.Companion.Main
import com.pob.umpa.ui.theme.UmpaColor.Companion.Secondary
import com.pob.umpa.ui.theme.UmpaColor.Companion.White
import com.pob.umpa.ui.theme.pretendardFontFamily

@Composable
fun MyPageScreen(navController: NavController) {
    Column(
        Modifier
            .padding(vertical = 56.dp)
            .wrapContentHeight()
            .verticalScroll(rememberScrollState())
            .background(White),
    ) {
        MyPageTitle(Modifier, "프로필 관리")
        ProfileCard(navController, Modifier, teacherProfileDummy)

        Spacer(modifier = Modifier.padding(4.dp))

        MenuItem(Modifier, "레슨 소개 관리", switch = false)
        MenuItem(Modifier, title = "레슨 / 서비스 관리", switch = false, onClick = { navController.navigate("managementServiceScreen")} )
        MenuItem(Modifier, "매칭 서비스에 내 프로필 공개", true)
        HorizontalDivider(Modifier.padding(horizontal = 4.dp, vertical = 16.dp), thickness = 0.5.dp)

        MyPageTitle(Modifier, "커뮤니티")
        MenuItem(Modifier, "커뮤니티 작성글 / 댓글", false)
        HorizontalDivider(Modifier.padding(horizontal = 4.dp, vertical = 16.dp), thickness = 0.5.dp)

        MyPageTitle(Modifier, "설정")
        MenuItem(Modifier, "채팅 알림", true)
        MenuItem(Modifier, "푸시 알림", true)
        HorizontalDivider(Modifier.padding(horizontal = 4.dp, vertical = 16.dp), thickness = 0.5.dp)

        MyPageTitle(Modifier, "가이드")
        MenuItem(Modifier, "앱 버전", false)
        MenuItem(Modifier, "개인 정보 처리 방침", false)
        MenuItem(Modifier, "오픈 소스 라이선스", false)
        MenuItem(Modifier, "이용 약관", false)


    }
}

@Composable
fun MyPageTitle(modifier: Modifier, title: String) {
    Text(
        modifier = modifier.padding(32.dp),
        text = title,
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )
}

@Composable
fun ProfileCard(navController: NavController, modifier: Modifier, teacher: TeacherProfile) {
    Column(
        Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .border(width = 1.dp, shape = RoundedCornerShape(10.dp), color = Main)
    ) {
        Column(modifier.padding(16.dp)) {
            TeacherProfileTop(modifier, teacher)
            CareerList(modifier, teacher.career)
            Spacer(modifier = modifier.padding(4.dp))
            Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                Text("가입일자 : ", fontSize = 10.sp, color = Grey)
                Text(formatDate(teacher.date), fontSize = 10.sp, color = Grey)
                Spacer(modifier.weight(1f))
                Button(
                    onClick = { navController.navigate("editProfile") },
                    modifier.size(100.dp, 35.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(Main)
                ) {
                    Text("프로필 수정", fontSize = 10.sp)
                }
            }


        }
    }

}

@Composable
fun TeacherProfileTop(modifier: Modifier, teacher: TeacherProfile) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(70.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(teacher.photo),
            contentDescription = "Teacher Profile Photo",
            Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Column(
            Modifier
                .padding(start = 8.dp)
                .wrapContentSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Row {
                Text(
                    teacher.name,
                    fontFamily = pretendardFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                Spacer(modifier.padding(2.dp))
                Text(
                    "선생님",
                    fontFamily = pretendardFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.size(4.dp))
            Row {
                Text(text = teacher.major, fontSize = 12.sp, color = Color.Gray)
                Spacer(modifier = modifier.padding(2.dp))
                Text(teacher.location, fontSize = 12.sp, color = Color.Gray)
            }

        }
    }
}



@Composable
fun CareerList(modifier: Modifier, career: TeacherCareer) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 50.dp, max = 200.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        career.career.forEachIndexed { index, careerItem ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painterResource(R.drawable.check),
                    contentDescription = null,
                    modifier
                        .size(12.dp)
                )
                Spacer(modifier = modifier.width(4.dp))
                Text(text = careerItem, fontFamily = pretendardFontFamily, fontSize = 12.sp)
            }
            if (index != career.career.lastIndex) {
                Spacer(modifier = Modifier.size(4.dp))
            }
        }
    }
}

@Composable
fun MenuItem(
    modifier: Modifier,
    title: String,
    switch: Boolean,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 32.dp)
            .clickable{ onClick?.invoke() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, fontFamily = pretendardFontFamily, fontSize = 12.sp)
        Spacer(modifier.weight(1f))
        if (switch) {
            MenuSwitch(modifier)
        }
    }
}

@Composable
fun MenuSwitch(modifier: Modifier) {
    var isChecked by remember { mutableStateOf(true) }

    Switch(
        checked = isChecked,
        onCheckedChange = { isChecked = it },
        colors = SwitchDefaults.colors(
            checkedTrackColor = Main,
            checkedThumbColor = Secondary
        ),
        modifier = modifier.scale(0.8f)

    )

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyPageScreenPreview() {
    MyPageScreen(navController = NavController(LocalContext.current))
}