package com.pob.umpa.ui.view.main.sign

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pob.umpa.R
import com.pob.umpa.ui.theme.UmpaTheme

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
            painter = painterResource(id = R.drawable.login_naver),
            contentDescription = "네이버 로그인"
        )
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