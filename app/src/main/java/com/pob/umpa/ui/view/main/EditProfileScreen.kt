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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.theme.UmpaColor.Companion.Grey
import com.pob.umpa.ui.theme.UmpaColor.Companion.Main
import com.pob.umpa.ui.theme.UmpaColor.Companion.White
import com.pob.umpa.ui.theme.pretendardFontFamily
import kotlin.math.exp

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
            EditProfileTitle(modifier = Modifier, title = "프로필 사진",true)
            ProfilePhoto {}

            Row(){
                Column(Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    EditProfileTitle(Modifier, "전공 과목",true)
                    SpinnerWithButton(optionList = subjectList, "전공 선택")
                }
                Column(Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    EditProfileTitle(Modifier, "레슨지역",true)
                    SpinnerWithButton(optionList = locationList, "레슨 지역")
                }
            }

            EditProfileTitle(modifier = Modifier, title = "대표 문구",true )
            EditText("대표 문구를 입력하세요")

            EditProfileTitle(modifier = Modifier, title = "사이트 링크" ,false)
            DefaultButton(text = "링크 추가하기")

            EditProfileTitle(modifier = Modifier, title = "경력 사항" ,false)
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

            EditProfileTitle(modifier = Modifier, title = "소개글",true)
            EditText("소개글을 입력해주세요")





        }



    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditProfilePreview() {
    EditProfileScreen()
}

