package com.pob.umpa.ui.view.main

import com.pob.umpa.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class TeacherProfile(
    val name: String,
    val photo : Int,
    val major : String,
    val location : String,
    val career : TeacherCareer,
    val date : Date,
)

data class TeacherCareer(
    val career : List<String>
)

val dateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
val joinDate: Date = dateFormat.parse("2024.11.09")!!

fun formatDate(date: Date):String{
    return dateFormat.format(date)
}



val teacherCareerListDummy = TeacherCareer(
    listOf(
        "서울예술대학교 베이스 전공 재학중",
        "호원대학교 베이스 전공 중퇴",
        "AKMU, Big Naughty, NCT WISH 등 녹음/라이브 세션",
        "서울예대, 동아방송대, 호원대, 서경대, 홍익대 합격 다수")
)



val teacherProfileDummy = TeacherProfile("장우영", R.drawable.hingkku, "베이스","서울/연남동",
    teacherCareerListDummy, joinDate)
