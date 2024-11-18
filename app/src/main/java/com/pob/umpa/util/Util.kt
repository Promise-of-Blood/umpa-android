package com.pob.umpa.util

import android.icu.text.DecimalFormat

/**
 * 1000원 단위로 (,)를 찍어주는 함수
 * 예:
 * 10000 -> 10,000
 */
fun Int.toCommaString(): String {
    return DecimalFormat("#,###").format(this)
}