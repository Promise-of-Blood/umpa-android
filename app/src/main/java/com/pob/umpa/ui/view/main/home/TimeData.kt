package com.pob.umpa.ui.view.main.home

import java.time.DayOfWeek
import java.time.LocalDate

// 이번달 언젠지, 이번달 시작일은 무슨 요일인지, 이번달 마지막은 무슨 요일인지

fun getNowMonth() : LocalDate {
    return LocalDate.now()
}

// 0 일요일 ~ 6 토요일
fun getFirstDay() : Int {
    return getNowMonth().withDayOfMonth(1).dayOfWeek.value
}

fun getLastDay() : Int {
    return getNowMonth().withDayOfMonth(getNumOfDays()).dayOfWeek.value
}

fun getNumOfDays() : Int {
    return getNowMonth().lengthOfMonth()
}

val WeekKorean = listOf(
    "일", "월", "화", "수", "목", "금", "토"
)