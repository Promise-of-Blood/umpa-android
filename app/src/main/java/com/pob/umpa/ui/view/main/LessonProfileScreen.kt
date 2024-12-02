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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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

        //LessonPhoto {}

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

            DefaultButton(text = "커리큘럼 추가하기")


        }


    }
}

@Composable
fun CategoryTitle(modifier: Modifier, title: String) {
    Row() {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = title,
            fontFamily = pretendardFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
    }

}

@Composable
fun descriptionText(modifier: Modifier, text: String) {
    Text(
        text = text,
        fontFamily = pretendardFontFamily,
        fontSize = 10.sp,
        color = Main
    )
}


@Composable
fun LessonPhoto(onImageSelected: (Uri?) -> Unit) {
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
                .fillMaxWidth()
                .aspectRatio(1f)
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
                    imageVector = Icons.Default.Create,
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
fun CheckBoxText(text: String) {
    var checked by remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically) {

        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it },
            colors = CheckboxDefaults.colors(
                checkedColor = Main,
                uncheckedColor = Grey,
                checkmarkColor = White
            ),
            modifier = Modifier.size(30.dp)
        )

        Text(text = text, fontSize = 12.sp)
    }
}

val timeList = (0..23).map { it.toString().padStart(2, '0') }


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


@Composable
fun BorderedLazyColumn(
    optionList: List<String>,
    selectedOption: String,
    width: Dp = 100.dp,
    onDaySelected: (String) -> Unit,
    borderWidth: Dp = 1.dp
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(width)
            .height(64.dp)
            .padding(vertical = 8.dp, horizontal = 2.dp) // 외부 여백
            .drawBehind {
                val strokeWidth = borderWidth.toPx()
                val yTop = 0f + strokeWidth / 2
                val yBottom = size.height - strokeWidth / 2

                // 위 테두리
                drawLine(
                    color = UmpaColor.Grey,
                    start = Offset(0f, yTop),
                    end = Offset(size.width, yTop),
                    strokeWidth = strokeWidth
                )
                // 아래 테두리
                drawLine(
                    color = UmpaColor.Grey,
                    start = Offset(0f, yBottom),
                    end = Offset(size.width, yBottom),
                    strokeWidth = strokeWidth
                )
            }
            .padding(0.dp) // 내부 여백
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(10.dp), // LazyColumn 내부 여백
        ) {
            items(optionList) { option ->
                val isSelected = option == selectedOption
                Text(
                    text = option,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        color = UmpaColor.Grey,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(UmpaColor.White)
                        .clickable { onDaySelected(option) }
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun AddPhoto(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .border(1.dp, UmpaColor.Main, RoundedCornerShape(5.dp)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Default.PhotoCamera,
            contentDescription = null,
            tint = UmpaColor.Grey
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditLessonProfilePreview() {
    EditLessonProfileScreen()
}

