package com.pob.umpa.ui.view.main.sign

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.theme.UmpaTheme
import com.pob.umpa.ui.theme.pretendardFontFamily

@Composable
fun UserTypeSelectionScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "back"
                        )
                    }
                },
                backgroundColor = UmpaColor.White
            )

        }
    ) { paddingValues ->

        var userType by remember { mutableStateOf("student") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(UmpaColor.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "앱의 이용 목적에 따라\n선택 해주세요",
                    modifier = Modifier
                        .fillMaxWidth(),
                    fontFamily = pretendardFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    lineHeight = 36.sp
                )
            }


            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
                    .background(UmpaColor.LightBlue)
                    .padding(horizontal = 20.dp)
                    .padding(top = 30.dp)
                    .weight(2.5f),
            ) {

                if (userType == "student") {
                    StudentDescription()
                } else {
                    TeacherDescription()
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 30.dp)
            ) {
                Button(
                    onClick = {
                        if (userType != "student") userType = "student"
                    },
                    shape = RoundedCornerShape(3.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (userType == "student") UmpaColor.White else UmpaColor.Main,
                        contentColor = if (userType == "student") UmpaColor.Main else UmpaColor.White
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .border(
                            width = 1.dp,
                            color = if (userType == "student") UmpaColor.Main else UmpaColor.White,
                            RoundedCornerShape(3.dp)
                        )
                ) {
                    Text(
                        text = "학생 회원",
                        fontFamily = pretendardFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))

                Button(
                    onClick = { if (userType != "teacher") userType = "teacher" },
                    shape = RoundedCornerShape(3.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .border(
                            width = 1.dp,
                            color = if (userType != "teacher") UmpaColor.White else UmpaColor.Main,
                            RoundedCornerShape(3.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (userType != "teacher") UmpaColor.Main else UmpaColor.White,
                        contentColor = if (userType != "teacher") UmpaColor.White else UmpaColor.Main
                    ),
                ) {
                    Text(
                        text = "선생님 회원",
                        fontFamily = pretendardFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                }

            }

            Button(
                onClick = {
                    if (userType == "student") navController.navigate("set_name") else navController.navigate(
                        "set_student_nickname"
                    )
                },
                shape = RoundedCornerShape(3.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = UmpaColor.Main,
                    contentColor = UmpaColor.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(
                        width = 1.dp,
                        color = UmpaColor.Main,
                        RoundedCornerShape(3.dp)
                    )
            ) { Text(text = "다음") }
        }


    }
}

@Composable
fun StudentDescription(modifier: Modifier = Modifier) {
    Text(
        text = "학생회원은! \n",
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    )

    Text(
        text = "매칭기능을 통해\n" +
                "선생님 / 입시 반주자 / 악보제작자를 살펴보고 구할 수 있어요!\n" +
                "\n커뮤니티를 통해 다른 사람들의 입시 후기를 확인 할 수 있어요\n" +
                "\n등등 회원 설명 써놓기~~",
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 20.sp
    )
}

@Composable
fun TeacherDescription(modifier: Modifier = Modifier) {
    Text(
        text = "선생님 회원은! \n",
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    )

    Text(
        text = "매칭기능을 통해\n" +
                "선생님 / 입시 반주자 / 악보제작자를 등록하고 학생을 구할 수 있어요!\n" +
                "\n커뮤니티를 통해 다른 사람들의 입시 후기를 확인 할 수 있어요\n" +
                "\n등등 회원 설명 써놓기~~",
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 20.sp
    )
}

@Preview(showBackground = true)
@Composable
fun UserTypeSelectionScreenPreview() {
    UmpaTheme {
        val navController = rememberNavController()
        UserTypeSelectionScreen(navController)
    }
}