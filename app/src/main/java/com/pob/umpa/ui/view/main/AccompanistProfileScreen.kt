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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.theme.pretendardFontFamily


@Composable
fun AccompanistProfileScreen(){

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(UmpaColor.White)
    ) {

        LessonPhoto{}

        Column(
            Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            EditProfileTitle(modifier = Modifier, title = "반주자 소개 제목" , isRequired = true)
            EditText(defaultText = "반주자 소개글의 제목을 입력해주세요")

            EditProfileTitle(modifier = Modifier, title = "반주 악기" , isRequired = true)
            SpinnerWithButton(optionList = subjectList , defaultText = "전공 선택" )

            EditProfileTitle(modifier = Modifier, title = "반주 비용" , isRequired = true,"학교별/ 곡 개수 별로 비용 추가가 있는 경우 추가 설명에 기재해 주세요")

            Row(verticalAlignment = Alignment.CenterVertically){
                EditText(defaultText = "반주 비용을 입력해주세요", 240.dp)
                Spacer(Modifier.size(12.dp))
                Text("/학교", fontFamily = pretendardFontFamily, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = UmpaColor.Grey)
            }

            Text("추가 설명", fontFamily = pretendardFontFamily, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            EditText(defaultText = "가격 정책에 대한 추가 설명을 기재 해주세요")

            HorizontalDivider(Modifier.padding(vertical = 16.dp), thickness = 0.5.dp)



            CategoryTitle(Modifier, "반주 정보")

            EditProfileTitle(modifier = Modifier, title = " 진행 방식" , isRequired = true)
            CheckBoxText(text = "연습 합주 제공")
            Row(verticalAlignment = Alignment.CenterVertically) {
                EditText(defaultText = "", 240.dp)
                Spacer(modifier = Modifier.size(12.dp))
                Text("회", fontFamily = pretendardFontFamily, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = UmpaColor.Grey)
            }
            CheckBoxText(text = "연습 합주 비용")
            Row(verticalAlignment = Alignment.CenterVertically) {
                EditText(defaultText = "", 240.dp)
                Spacer(modifier = Modifier.size(12.dp))
                Text("회 당", fontFamily = pretendardFontFamily, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = UmpaColor.Grey)
            }


            EditProfileTitle(modifier = Modifier, title = "연습용 MR" , isRequired = true )
            CheckBoxText(text = "연습용 MR(또는 녹음본) 제공")

            EditProfileTitle(modifier = Modifier, title = "합주 위치", isRequired = true)
            CheckBoxText(text = "개인 작업실에서 진행")
            EditText(defaultText = "주소 검색")
            Row (
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End) {
                CheckBoxText(text = "주소 공개")
                Spacer(modifier = Modifier.size(12.dp))
                CheckBoxText(text = "레슨 확정 후 주소 공개")
            }

            EditProfileTitle(modifier = Modifier, title = "서비스 소개" , isRequired = true)
            EditText(defaultText = "서비스에 대한 부가적인 설명을 적어주세요")
        }

    }



}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AccompanistProfilePreview() {
    AccompanistProfileScreen()
}