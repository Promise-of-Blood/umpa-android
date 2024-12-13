package com.pob.umpa.ui.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.theme.UmpaColor.Companion.Main

@Composable
fun MrMakingProfileScreen() {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(UmpaColor.White)
    ) {

        LessonPhoto {}

        Column(
            Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            MRIntroduceTitle()

            MRMakingCost()

            HorizontalDivider(Modifier.padding(vertical = 16.dp), thickness = 0.5.dp)

            CategoryTitle(modifier = Modifier, title = "서비스 정보")

            ProductionGuide()

            val programList = listOf("로직", "큐베이스", "에이블톤", "그 외")
            UsingProgram(programList = programList)

            ServiceIntroduce()

            HorizontalDivider(Modifier.padding(vertical = 16.dp), thickness = 0.5.dp)

            CategoryTitle(modifier = Modifier, title = "샘플 등록")

            SampleVideoUpload()


        }

    }
}

@Composable
fun MRIntroduceTitle() {
    EditProfileTitle(modifier = Modifier, title = "MR 제작 소개 제목", isRequired = true)
    EditText(defaultText = "MR 제작 소개 글의 제목을 입력해 주세요")
}

@Composable
fun MRMakingCost() {
    EditProfileTitle(
        modifier = Modifier,
        title = "제작 비용",
        isRequired = true,
        "기본 곡당 가격을 기재해 주세요. \n트랙 별로 추가적인 가격 정책이 있다면 기재해 주세요"
    )
    Row(verticalAlignment = Alignment.CenterVertically) {

        EditText(defaultText = "가격을 적어주세요", 200.dp)
        val optionTypeList = listOf("/곡", "/트랙")
        var selectedSongOrTrack by remember {
            mutableStateOf(optionTypeList[0])
        }

        BorderedLazyColumn(
            optionList = optionTypeList,
            selectedOption = selectedSongOrTrack,
            70.dp,
            onDaySelected = { option -> selectedSongOrTrack = option })
    }

    Text(text = "추가 설명", fontSize = 12.sp)

    EditText(defaultText = "가격 정책에 대한 추가 설명을 기재해 주세요")


}

@Composable
fun SampleVideoUpload() {

    val linkStates = remember {
        mutableStateListOf<Int>()
    }

    if (linkStates.isEmpty()) {
        linkStates.add(0)
    }

    Column {

        EditProfileTitle(modifier = Modifier, title = "샘플 음원 등록", isRequired = false)
        Spacer(modifier = Modifier.size(12.dp))

        linkStates.forEach { _ ->
            EditText(defaultText = "유튜브 링크를 입력해주세요")
            Spacer(modifier = Modifier.size(8.dp))
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = {
                    linkStates.add(linkStates.size)
                }
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = null,
                    tint = Main
                )

            }

        }

    }


}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MrMakingProfilePreview() {
    MrMakingProfileScreen()
}