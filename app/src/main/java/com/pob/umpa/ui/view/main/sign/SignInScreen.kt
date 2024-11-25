package com.pob.umpa.ui.view.main.sign

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pob.umpa.R
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.theme.UmpaTheme
import com.pob.umpa.ui.theme.pretendardFontFamily

@Composable
fun SignInScreen(navController: NavHostController) {
    Column {
        //Todo Logo
        //Todo SignIn
        SignIn(modifier = Modifier.fillMaxSize())
        //Todo find Id/password
        //Todo Social Login
    }
}

@Composable
fun SignIn(modifier: Modifier) {
    Column(
        modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.kakao_login_large_wide),
            contentDescription = "카카오 로그인",
            modifier = Modifier.size(340.dp, 60.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        LoginBtn(
            signType = "네이버",
            btnColor = 0xFF03C75A,
            logo = R.drawable.btn_naver_icon_circle,
            borderColor = 0xFF03C75A,
            textColor = UmpaColor.White
        )

        Spacer(modifier = Modifier.height(12.dp))

        LoginBtn(
            signType = "구글",
            btnColor = 0xFFFFFFFF,
            logo = R.drawable.android_light_rd_na3x,
            borderColor = 0xFF9E9E9E,
            textColor = UmpaColor.Black
        )
    }
}

@Composable
fun LoginBtn(
    modifier: Modifier = Modifier,
    signType: String,
    btnColor: Long,
    logo: Int,
    borderColor: Long,
    textColor: Color
) {

    Row(
        modifier = Modifier
            .size(width = 340.dp, height = 50.dp)
            .border(width = 1.dp, color = UmpaColor.Grey, shape = RoundedCornerShape(8.dp))
            .clip(
                RoundedCornerShape(8.dp)
            )
            .background(color = Color(btnColor)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = logo),
            contentDescription = "$signType 로그인 로고",
            modifier = Modifier.weight(0.15f)
        )


        Text(
            text = "${signType}로 시작하기",
            modifier = Modifier.weight(0.75f),
            fontFamily = pretendardFontFamily,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = textColor
        )

        Spacer(modifier = Modifier.weight(0.1f))
    }


}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    UmpaTheme {
        val navController = rememberNavController()
        SignInScreen(navController)
    }
}