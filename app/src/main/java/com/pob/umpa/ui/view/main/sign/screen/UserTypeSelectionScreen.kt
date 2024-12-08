package com.pob.umpa.ui.view.main.sign.screen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.theme.UmpaTheme
import com.pob.umpa.ui.theme.pretendardFontFamily
import com.pob.umpa.ui.view.main.sign.SignViewModel
import com.pob.umpa.ui.view.main.sign.UserType

@Composable
fun UserTypeSelectionScreen(
    navController: NavHostController,
    viewModel: SignViewModel = viewModel()
) {
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

        val userType by viewModel.userType

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

                if (userType == UserType.STUDENT) {
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

                UserTypeButton(
                    modifier = Modifier.weight(1f),
                    viewModel = viewModel,
                    userType = UserType.STUDENT,
                    selectedType = userType
                )
                UserTypeButton(
                    modifier = Modifier.weight(1f),
                    viewModel = viewModel,
                    userType = UserType.TEACHER,
                    selectedType = userType
                )

            }

            Button(
                onClick = {
                    navController.navigate("set_name")
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
fun UserTypeButton(
    modifier: Modifier = Modifier,
    viewModel: SignViewModel,
    userType: UserType,
    selectedType: UserType
) {
    Button(
        onClick = { if (userType != selectedType) viewModel.setUserType(userType) },
        shape = RoundedCornerShape(3.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (userType == selectedType) UmpaColor.White else UmpaColor.Main,
            contentColor = if (userType == selectedType) UmpaColor.Main else UmpaColor.White
        ),
        modifier = modifier
            .height(50.dp)
            .border(
                width = 1.dp,
                color = if (userType == selectedType) UmpaColor.Main else UmpaColor.White,
                RoundedCornerShape(3.dp)
            )
    ) {
        Text(
            text = if (userType == UserType.STUDENT) "학생 회원" else "선생님 회원",
            fontFamily = pretendardFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
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