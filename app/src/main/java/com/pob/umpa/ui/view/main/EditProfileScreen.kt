package com.pob.umpa.ui.view.main

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.pob.umpa.ui.theme.Grey
import com.pob.umpa.ui.theme.White
import com.pob.umpa.ui.theme.pretendardFontFamily
import com.pob.umpa.ui.theme.Main as Main

val subjectList = listOf("보컬", "피아노", "작곡", "기타", "드럼", "베이스", "관악", "전자 음악", "화성학")
val locationList = listOf("서울/대전/대구/부산/광주/경기도")

@Composable
fun EditProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(White)
    ) {
        Column(
            Modifier
                .padding(horizontal = 16.dp, vertical = 32.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            EditProfileTitle(modifier = Modifier, title = "프로필 사진")
            ProfilePhoto {}

            Row(){
                Column(Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    EditProfileTitle(Modifier, "전공 과목")
                    SpinnerWithButton(optionList = subjectList, "전공 선택")
                }
                Column(Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    EditProfileTitle(Modifier, "레슨지역")
                    SpinnerWithButton(optionList = locationList, "레슨 지역")
                }
            }

            EditProfileTitle(modifier = Modifier, title = "대표 문구" )
            EditText("대표 문구를 입력하세요")

            EditProfileTitle(modifier = Modifier, title = "사이트 링크" )
            DefaultButton(text = "링크 추가하기")

            EditProfileTitle(modifier = Modifier, title = "경력 사항" )
            EditText("대표 경력 사항을 입력해주세요")
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = null,
                    tint = Main
                )
            }

            EditProfileTitle(modifier = Modifier, title = "소개글")
            EditText("소개글을 입력해주세요")





        }



    }
}

@Composable
fun EditProfileTitle(modifier: Modifier, title: String) {
    Row() {
        Text(
            text = title,
            fontFamily = pretendardFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Spacer(modifier.size(8.dp))
        Icon(
            modifier = Modifier.size(18.dp),
            imageVector = Icons.Default.Info,
            contentDescription = null,
            tint = Main
        )
    }

}

@Composable
fun ProfilePhoto(onImageSelected: (Uri?) -> Unit) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
        onImageSelected(uri)
    }

    Column(
        modifier = Modifier
            .wrapContentSize()
    ) {
        // 프로필 사진 영역
        Box(
            modifier = Modifier
                .size(180.dp)
                .clip(shape = RoundedCornerShape(5.dp))
                .background(Grey)
                .clickable { launcher.launch("image/*") } // 갤러리 호출
        ) {
            selectedImageUri?.let { uri ->
                // 선택된 이미지 표시
                Image(
                    painter = rememberAsyncImagePainter(uri),
                    contentDescription = "Selected Profile Picture",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } ?: run {
                // 기본 이미지 또는 빈 상태 표시
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Default Profile Picture",
                    modifier = Modifier.align(Alignment.Center),
                    tint = White
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun SpinnerWithButton(optionList: List<String>,defaultText : String) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(defaultText) }

    Column(
        modifier = Modifier
            .wrapContentWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(onClick = { expanded = true }, modifier = Modifier.width(120.dp)) {
            Row {
                Text(selectedOption, color = Grey)
                Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Grey)
            }
        }
    }

    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
        optionList.forEach { option ->
            DropdownMenuItem(
                onClick = {
                    selectedOption = option
                    expanded = false
                }
            ) {
                Text(option)
            }
        }
    }
}

@Composable
fun EditText(defaultText: String){
    var value by remember { mutableStateOf(defaultText) }
    TextField(
        value = value,
        onValueChange = {value = it},
        textStyle = TextStyle(color = Grey),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .border(width = 1.dp, color = Main, shape = RoundedCornerShape(5.dp))
         , colors = TextFieldDefaults.outlinedTextFieldColors(
             backgroundColor = White
         )
    )
}

@Composable
fun DefaultButton(text : String){
    Button(onClick = { /*TODO*/ }, modifier = Modifier
        .fillMaxWidth()
        .height(50.dp), colors = ButtonDefaults.buttonColors(Main) , shape = RoundedCornerShape(5.dp)) {
        Text(text = text)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditProfilePreview() {
    EditProfileScreen()
}

