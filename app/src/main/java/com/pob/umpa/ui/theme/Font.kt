package com.pob.umpa.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.pob.umpa.R

// !!! 폰트 패밀리 사용법 예시 !!!
// 텍스트 같은 데다 쓸 때, 다음과 같이 작성하면 됩니다!!!!! 파팅!!!
// fontFamily = pretendardFontFamily,
// fontWeight = FontWeight.Black

val pretendardFontFamily = FontFamily(
    Font(R.font.pretendard_black, FontWeight.Black),
    Font(R.font.pretendard_bold, FontWeight.Bold),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_light, FontWeight.Light),
    Font(R.font.pretendard_thin, FontWeight.Thin),
    Font(R.font.pretendard_extra_bold, FontWeight.ExtraBold),
    Font(R.font.pretendard_extra_light, FontWeight.ExtraLight),
    Font(R.font.pretendard_semi_bold, FontWeight.SemiBold),
)