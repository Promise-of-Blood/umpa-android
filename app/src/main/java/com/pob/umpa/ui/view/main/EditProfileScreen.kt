package com.pob.umpa.ui.view.main


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pob.umpa.ui.theme.UmpaColor.Companion.Main
import com.pob.umpa.ui.theme.UmpaColor.Companion.White


val subjectList = listOf("보컬", "피아노", "작곡", "기타", "드럼", "베이스", "관악", "전자 음악", "화성학")
val locationList = listOf("서울","대전","대구","부산","광주","경기도")

@Composable
fun EditProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 56.dp)
            .verticalScroll(rememberScrollState())
            .background(White)
    ) {
        Column(
            Modifier
                .padding(horizontal = 32.dp, vertical = 32.dp)
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
            DefaultButton(text = "링크 추가하기", { })

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

