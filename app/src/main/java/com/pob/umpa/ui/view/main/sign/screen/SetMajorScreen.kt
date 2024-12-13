package com.pob.umpa.ui.view.main.sign.screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pob.umpa.common.InstrumentsTypeList
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.theme.UmpaTheme
import com.pob.umpa.ui.theme.pretendardFontFamily
import com.pob.umpa.ui.view.main.sign.SignViewModel
import com.pob.umpa.ui.view.main.sign.UserType


@Composable
fun SetMajorScreen(navController: NavHostController, viewModel: SignViewModel) {

    val userType by viewModel.userType

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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {

                Spacer(modifier = Modifier.height(100.dp))

                Text("전공을 선택해 주세요", fontSize = 30.sp)

                Spacer(modifier = Modifier.height(200.dp))

                SignMajorDropDownMenu(dropMenuList = InstrumentsTypeList)


            }

            Button(
                onClick = {
                    when (userType) {
                        UserType.STUDENT -> navController.navigate("sign_end")
                        UserType.TEACHER -> navController.navigate("set_student_university")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(0.dp)
            ) { Text("다음") }
        }
    }

}

@Composable
fun SignMajorDropDownMenu(modifier: Modifier = Modifier, dropMenuList: List<String>) {

    val context = LocalContext.current

    var isDropDownMenuExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(dropMenuList[0]) }

    OutlinedButton(
        onClick = { isDropDownMenuExpanded = true },
        border = BorderStroke(1.dp, Color.LightGray),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),

        ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            Text(
                text = selectedText,
                color = Color.Gray,
                fontFamily = pretendardFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )

            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Drop-down Arrow",
                tint = Color.Gray
            )


        }
    }

    DropdownMenu(
        modifier = Modifier.wrapContentSize(),
        expanded = isDropDownMenuExpanded,
        onDismissRequest = { isDropDownMenuExpanded = false }
    ) {

        dropMenuList.forEach { option ->

            DropdownMenuItem(text = { Text(text = option, fontSize = 16.sp) },
                onClick = {
                    Toast.makeText(context, option, Toast.LENGTH_SHORT).show()
                    isDropDownMenuExpanded = false
                    selectedText = option
                },
                trailingIcon = {
                    if (option == selectedText) Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null
                    )
                }
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun SetMajorScreenPreview() {
    UmpaTheme {
        val navController = rememberNavController()
        SetMajorScreen(navController, viewModel = hiltViewModel())
    }
}