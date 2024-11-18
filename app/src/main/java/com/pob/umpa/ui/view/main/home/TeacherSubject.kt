package com.pob.umpa.ui.view.main.home

import com.pob.umpa.R

data class TeacherSubject (
    val type : String,
    val name : String,
    val image : Int,
)

val TeacherSubjectList = listOf<TeacherSubject>(

    TeacherSubject("piano", "피아노", R.drawable.find_teacher_piano),
    TeacherSubject("vocal", "보컬", R.drawable.find_teacher_vocal),
    TeacherSubject("composition", "작곡", R.drawable.find_teacher_composition),
    TeacherSubject("guitar", "기타", R.drawable.find_teacher_guitar),
    TeacherSubject("bass", "베이스", R.drawable.find_teacher_bass),

    TeacherSubject("drum", "드럼", R.drawable.find_teacher_drum),
    TeacherSubject("wind", "관악", R.drawable.find_teacher_wind),
    TeacherSubject("electronic", "전자음악", R.drawable.find_teacher_electronic),
    TeacherSubject("harmony", "화성학", R.drawable.find_teacher_harmony),
    TeacherSubject("accompanist", "반주자", R.drawable.find_teacher_accompanist),

)