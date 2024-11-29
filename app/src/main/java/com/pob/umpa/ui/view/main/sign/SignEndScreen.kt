package com.pob.umpa.ui.view.main.sign

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pob.umpa.R
import com.pob.umpa.ui.theme.UmpaTheme
import com.pob.umpa.ui.theme.pretendardFontFamily

@Composable
fun SignEndScreen(navController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "환영합니다 \n음파에서 어쩌구저쩌구 \n해보세요",
                fontSize = 30.sp,
                fontFamily = pretendardFontFamily,
                fontWeight = FontWeight.Bold,
                lineHeight = 30.sp
            )

            Spacer(modifier = Modifier.height(30.dp))

            Image(
                painter = painterResource(R.drawable.hingkku),
                contentDescription = null,
                modifier = Modifier.clip(RoundedCornerShape(15.dp))
            )
        }

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(0.dp)
        ) { Text("회원가입 완료") }
    }


}


@Preview(showBackground = true)
@Composable
fun SignEndScreenPreview() {
    UmpaTheme {
        val navController = rememberNavController()
        SignEndScreen(navController)
    }
}