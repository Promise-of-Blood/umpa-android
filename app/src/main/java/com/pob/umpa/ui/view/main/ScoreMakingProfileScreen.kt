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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.theme.UmpaColor.Companion.Main

val subjectOptions = listOf("보컬", "피아노", "드럼", "베이스", "기타", "풀 스코어", "전자 음악")

@Composable
fun ScoreMakingProfileScreen() {

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
            EditProfileTitle(modifier = Modifier, title = "악보 제작 소개 제목", isRequired = true)
            EditText(defaultText = "반주자 소개 글의 제목을 입력 해주세요")

            EditProfileTitle(modifier = Modifier, title = "악보 제작 전공", isRequired = true)
            MultiSelectChips(subjectOptions)

            ProductionCost()

            HorizontalDivider(Modifier.padding(vertical = 16.dp), thickness = 0.5.dp)

            CategoryTitle(modifier = Modifier, title = "서비스 정보" )

            ProductionGuide()

            val programList = listOf("시벨리우스","피날레","그 외")
            UsingProgram(programList)

            ServiceIntroduce()

            HorizontalDivider(Modifier.padding(vertical = 16.dp), thickness = 0.5.dp)

            CategoryTitle(modifier = Modifier, title = "샘플 등록")

            SampleSheetUpload()



        }

    }


}

@Composable
fun ProductionCost() {

    val columnStates = remember {
        mutableStateListOf<Int>()
    }

    if (columnStates.isEmpty()) {
        columnStates.add(0)
    }

    Column {
        EditProfileTitle(
            modifier = Modifier,
            title = "제작 비용",
            isRequired = true,
            "전공 별로 가격이 다를 경우 전공 별로 가격을 기재해주세요 \n가장 위에 설정한 가격이 대표 가격으로 표시됩니다."
        )

        columnStates.forEach { _ ->

            val optionTypeList = listOf("/장", "/곡")
            var selectedSubject by remember { mutableStateOf(subjectOptions[0]) }
            var selectedType by remember { mutableStateOf(optionTypeList[0]) }

            Row(verticalAlignment = Alignment.CenterVertically) {
                BorderedLazyColumn(
                    optionList = subjectList,
                    selectedOption = selectedSubject,
                    100.dp,
                    onDaySelected = { option -> selectedSubject = option })
                Spacer(modifier = Modifier.size(4.dp))
                EditText(defaultText = "제작 비용을 입력해주세요", 180.dp)
                Spacer(modifier = Modifier.size(4.dp))
                BorderedLazyColumn(
                    optionList = optionTypeList,
                    selectedOption = selectedType,
                    70.dp,
                    onDaySelected = { option -> selectedType = option })
            }

        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = {
                    columnStates.add(columnStates.size)
                }
            ){
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


@Composable
fun ProductionGuide(){
    Column {
        EditProfileTitle(modifier = Modifier, title = "진행 방식" , isRequired = true)
        Spacer(modifier = Modifier.size(16.dp))

        Text(text = "평균 소요 기간", fontSize = 12.sp)
        Spacer(modifier = Modifier.size(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically){
            EditText(defaultText = "", 80.dp)
            val optionList = listOf("일","주","달")
            var selectedDurationMin by remember {
                mutableStateOf(optionList[0])
            }
            var selectedDurationMax by remember {
                mutableStateOf(optionList[0])
            }
            Spacer(modifier = Modifier.size(2.dp))

            BorderedLazyColumn(optionList = optionList , selectedOption = selectedDurationMin , width = 80.dp, onDaySelected = {option -> selectedDurationMin = option} )
            Spacer(modifier = Modifier.size(8.dp))

            Text(text = "~", fontSize = 20.sp, color = UmpaColor.Grey)
            Spacer(modifier = Modifier.size(8.dp))

            EditText(defaultText = "", 80.dp)
            Spacer(modifier = Modifier.size(2.dp))
            BorderedLazyColumn(optionList = optionList , selectedOption = selectedDurationMax , width = 80.dp, onDaySelected = {option -> selectedDurationMax = option} )

        }

        Spacer(modifier = Modifier.size(12.dp))

        CheckBoxText(text = "무료 수정 제공")
        Spacer(modifier = Modifier.size(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically){
            EditText(defaultText = "", 100.dp)
            Spacer(modifier = Modifier.size(8.dp))
            Text("회", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = UmpaColor.Grey)
        }
    }

}

@Composable
fun UsingProgram(programList : List<String>){
    EditProfileTitle(modifier = Modifier, title = "제작 프로그램", isRequired = false )

    programList.forEach { program ->
        CheckBoxText(text = program)
    }
    EditText(defaultText = "사용하시는 프로그램을 입력해주세요")
}

@Composable
fun ServiceIntroduce(){
    EditProfileTitle(modifier = Modifier, title = "서비스 소개" , isRequired = true)
    EditText(defaultText = "서비스에 대한 부가적인 설명을 적어주세요")
}

@Composable
fun SampleSheetUpload(){
    EditProfileTitle(modifier = Modifier, title = "샘플 악보 등록" , isRequired = false, "샘플로 보여질 악보를 등록해주세요. \n악보에는 워터마크가 삽입되어서 보여집니다.")
    AddPhoto()

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScoreMakingProfilePreview() {
    ScoreMakingProfileScreen()
}