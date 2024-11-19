package com.pob.umpa.ui.view.main.sign

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pob.umpa.ui.theme.UmpaTheme

@Composable
fun SignInScreen(navController: NavHostController) {
    Column {
        //Todo Logo
        //Todo SignIn

        //Todo find Id/password
        //Todo Social Login
    }
}

@Composable
fun SignIn(modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    UmpaTheme {
        val navController = rememberNavController()
        SignInScreen(navController)
    }
}