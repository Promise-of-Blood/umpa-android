package com.pob.umpa.ui.view.main.sign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.pob.umpa.ui.theme.UmpaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UmpaTheme {
                SignNavigation()
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        UmpaTheme {
            val navController = rememberNavController()
            SignInScreen(navController)
        }
    }
}