package com.pob.umpa.util

import android.icu.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * 1000원 단위로 (,)를 찍어주는 함수
 * 예:
 * 10000 -> 10,000
 */
fun Int.toCommaString(): String {
    return DecimalFormat("#,###").format(this)
}

fun LocalDateTime.toFormattedString(pattern: String = "yyyy.MM.dd") =
    this.format(DateTimeFormatter.ofPattern(pattern))

fun LocalDateTime.toDiffString(): String {
    val now = LocalDateTime.now()

    val secondsDiff = ChronoUnit.SECONDS.between(this, now)
    val minutesDiff = secondsDiff / 60
    val hoursDiff = minutesDiff / 60
    val daysDiff = hoursDiff / 24
    val weeksDiff = daysDiff / 7
    val monthsDiff = daysDiff / 30
    val yearsDiff = daysDiff / 365

    return when {
        secondsDiff < 60 -> "${secondsDiff}초 전"
        minutesDiff < 60 -> "${minutesDiff}분전"
        hoursDiff < 24 -> "${hoursDiff}시간전"
        daysDiff < 7 -> "${daysDiff}일전"
        weeksDiff < 4 -> "${weeksDiff}주전"
        monthsDiff < 12 -> "${monthsDiff}달전"
        else -> "${yearsDiff}년전"
    }
}

/**
* Youtube 링크의 videoId를 추출해주는 함수
* ex) https://www.youtube.com/watch?v=3DeTW8FKKYg -> 3DeTW8FKKYg
* */
fun String.parseVideoLink() = this.split("v=")[1].split("&")[0]

/**
 * 리소스 이름을 리소스 ID로 변환하는 함수
 * 예:
 * val drawableName = "R.drawable.hingkku"
 * val drawableId = getDrawableId(drawableName)
 * Image(painter = painterResource(id = drawableId),
 */
@Composable
fun getDrawableId(resourceName: String): Int {
    val context = LocalContext.current
    return context.resources.getIdentifier(resourceName, "drawable", context.packageName)
}
