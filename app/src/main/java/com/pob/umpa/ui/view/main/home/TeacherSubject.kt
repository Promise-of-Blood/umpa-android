package com.pob.umpa.ui.view.main.home

import com.pob.umpa.R

data class TeacherSubject (
    val type : String,
    val name : String,
    val image : Int,
)

val TeacherSubjectList = listOf<TeacherSubject>(

    TeacherSubject("piano", "피아노", R.drawable.ic_launcher_foreground),
    TeacherSubject("vocal", "보컬", R.drawable.ic_launcher_foreground),
    TeacherSubject("composition", "작곡", R.drawable.ic_launcher_foreground),
    TeacherSubject("guitar", "기타", R.drawable.ic_launcher_foreground),
    TeacherSubject("bass", "베이스", R.drawable.ic_launcher_foreground),

    TeacherSubject("drum", "드럼", R.drawable.ic_launcher_foreground),
    TeacherSubject("wind", "관악", R.drawable.ic_launcher_foreground),
    TeacherSubject("electronic", "전자음악", R.drawable.ic_launcher_foreground),
    TeacherSubject("harmony", "화성학", R.drawable.ic_launcher_foreground),
    TeacherSubject("accompanist", "반주자", R.drawable.ic_launcher_foreground),

)