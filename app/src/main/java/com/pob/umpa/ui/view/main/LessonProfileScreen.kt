package com.pob.umpa.ui.view.main


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.theme.UmpaColor.Companion.Grey
import com.pob.umpa.ui.theme.UmpaColor.Companion.Main


val monToSun = listOf("월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일")

@Composable
fun EditLessonProfileScreen() {

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
            EditProfileTitle(modifier = Modifier, title = "레슨 소개 제목", true)
            EditText(defaultText = "레슨 소개글의 제목을 입력해주세요")


            Row() {
                Column(
                    Modifier.weight(.5f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    EditProfileTitle(Modifier, "레슨 과목", true)
                    SpinnerWithButton(optionList = subjectList, "전공 선택")
                }

            }

            EditProfileTitle(modifier = Modifier, title = "레슨 비용", true)
            Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.Bottom) {
                Box(Modifier.weight(1.2f)) {
                    EditText("레슨 비용을 입력 해주세요")
                }
                Box(
                    Modifier
                        .weight(1f)
                        .padding(start = 5.dp)
                ) {
                    Text(
                        text = "/시간",
                        fontWeight = FontWeight.SemiBold,
                        color = Grey,
                        fontSize = 20.sp
                    )
                }

            }

            HorizontalDivider(Modifier.padding(vertical = 16.dp), thickness = 0.5.dp)

            CategoryTitle(modifier = Modifier, title = "수업 소개")

            Schedule(modifier = Modifier)


            EditProfileTitle(modifier = Modifier, title = "수업 방식", true)
            descriptionText(modifier = Modifier, text = "* 다중 체크 가능")

            Row {
                CheckBoxText(text = "대면 수업")
                CheckBoxText(text = "비대면(화상)수업")
            }

            EditProfileTitle(modifier = Modifier, title = "시범 레슨 및 상담 운영", true)
            Column {
                Row {
                    CheckBoxText(text = "대면 상담 가능")
                    CheckBoxText(text = "대면 상담 불가능")
                }
                Row {
                    CheckBoxText(text = "시범 레슨 유료")
                    CheckBoxText(text = "시범 레슨 무료")
                    CheckBoxText(text = "시범 레슨 불가")

                }

            }
            EditProfileTitle(modifier = Modifier, title = "수업 소개", true)
            EditText(defaultText = "레슨에 대한 부가적인 설명을 적어주세요")

            EditProfileTitle(modifier = Modifier, title = "수업 대상", false)
            EditText(defaultText = "수업 대상에 적합한 학생상을 적어주세요")

            EditProfileTitle(modifier = Modifier, title = "작업실 사진", false)
            AddPhoto()

            HorizontalDivider(Modifier.padding(vertical = 16.dp), thickness = 0.5.dp)

            CategoryTitle(modifier = Modifier, title = "커리큘럼 소개")

            DefaultButton(text = "커리큘럼 추가하기" , {})
        }
    }
}


@Composable
fun Schedule(modifier: Modifier) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        EditProfileTitle(modifier = modifier, title = "일정", isRequired = true)
        CheckBoxText(text = "학생과 조율 후 결정")
        CheckBoxText(text = "요일 시간 지정")

        SetSchedule()


    }
}

@Composable
fun SetSchedule() {
    var selectedDay by remember { mutableStateOf(monToSun[0]) }
    var selectedStartTime by remember { mutableStateOf(timeList[0]) }
    var selectedStartMinute by remember { mutableStateOf(timeList[0]) }
    var selectedEndTime by remember { mutableStateOf(timeList[0]) }
    var selectedEndMinute by remember { mutableStateOf(timeList[0]) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BorderedLazyColumn(
                optionList = monToSun,
                selectedOption = selectedDay,
                90.dp,
                { option -> selectedDay = option })
            Spacer(modifier = Modifier.width(16.dp))
            BorderedLazyColumn(
                optionList = timeList,
                selectedOption = selectedStartTime,
                58.dp,
                { option -> selectedStartTime = option })
            BorderedLazyColumn(
                optionList = timeList,
                selectedOption = selectedStartMinute,
                58.dp,
                { option -> selectedStartMinute = option })
            Text(
                text = "~",
                style = TextStyle(color = UmpaColor.Grey),
                modifier = Modifier
                    .padding(8.dp)
            )
            BorderedLazyColumn(
                optionList = timeList,
                selectedOption = selectedEndTime,
                58.dp,
                { option -> selectedEndTime = option })
            BorderedLazyColumn(
                optionList = timeList,
                selectedOption = selectedEndMinute,
                58.dp,
                { option -> selectedEndMinute = option })
        }
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Default.AddCircle,
            contentDescription = null,
            tint = Main
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditLessonProfilePreview() {
    EditLessonProfileScreen()
}

