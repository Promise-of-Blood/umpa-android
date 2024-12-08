package com.pob.umpa.util

import android.icu.text.DecimalFormat
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