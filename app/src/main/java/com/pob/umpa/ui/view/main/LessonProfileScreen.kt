package com.pob.umpa.ui.view.main

import android.net.Uri
import android.widget.CheckBox
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.pob.umpa.ui.theme.Grey
import com.pob.umpa.ui.theme.Main
import com.pob.umpa.ui.theme.White
import com.pob.umpa.ui.theme.pretendardFontFamily

@Composable
fun EditLessonProfileScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(White)
    ) {

        LessonPhoto {}

        Column(

            Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            EditProfileTitle(modifier = Modifier, title = "레슨 소개 제목")
            EditText(defaultText = "레슨 소개글의 제목을 입력해주세요")


            Row(){
                Column(
                    Modifier.weight(.5f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    EditProfileTitle(Modifier, "레슨 과목")
                    SpinnerWithButton(optionList = subjectList, "전공 선택")
                }

            }

            EditProfileTitle(modifier = Modifier, title = "레슨 비용" )
            Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.Bottom){
                Box(Modifier.weight(1.2f)) {
                    EditText("레슨 비용을 입력 해주세요")
                }
                Box(
                    Modifier
                        .weight(1f)
                        .padding(start = 5.dp)) {
                    Text(text = "/시간", fontWeight = FontWeight.SemiBold, color = Grey, fontSize = 20.sp)
                }

            }

            HorizontalDivider(Modifier.padding(vertical = 16.dp),thickness = 0.5.dp)

            CategoryTitle(modifier = Modifier, title = "수업 소개")

            EditProfileTitle(modifier = Modifier, title = "수업 방식" )
            descriptionText(modifier = Modifier, text = "* 다중 체크 가능")

            Row {
                CheckBoxText(text = "대면 수업")
                CheckBoxText(text = "비대면(화상)수업" )
            }

            EditProfileTitle(modifier = Modifier, title = "시범 레슨 및 상담 운영")
            Column {
                Row {
                    CheckBoxText(text = "대면 상담 가능")
                    CheckBoxText(text = "대면 상담 불가능" )
                }
                Row {
                    CheckBoxText(text = "시범 레슨 유료")
                    CheckBoxText(text = "시범 레슨 무료" )
                    CheckBoxText(text = "시범 레슨 불가" )

                }

            }

            EditProfileTitle(modifier = Modifier, title = "레슨 내용")
            EditText(defaultText = "레슨에 대한 부가적인 설명을 적어주세요")

            HorizontalDivider(Modifier.padding(vertical = 16.dp),thickness = 0.5.dp)

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
fun descriptionText(modifier: Modifier, text: String){
 Text(
     text = text ,
     fontFamily = pretendardFontFamily,
     fontSize = 10.sp,
     color = Main )
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
fun CheckBoxText(text: String){
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
        )

        Text(text = text, fontSize = 12.sp)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditLessonProfilePreview() {
    EditLessonProfileScreen()
}

