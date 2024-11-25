package com.pob.umpa.ui.view.main.sign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.theme.UmpaTheme

@Composable
fun SetNameScreen(navController: NavHostController) {

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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(UmpaColor.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            var name by remember { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {

                Spacer(modifier = Modifier.height(100.dp))

                Text("이름을 입력해 주세요", fontSize = 30.sp)

                Spacer(modifier = Modifier.height(200.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = "이름", color = UmpaColor.Black) },
                    placeholder = { Text(text = "이름을 입력 해주세요", color = UmpaColor.Grey) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.AccountBox,
                            contentDescription = "user input icon"
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(0.dp)
            ) { Text("다음") }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun SetNameScreenPreview() {
    UmpaTheme {
        val navController = rememberNavController()
        SetNameScreen(navController)
    }
}