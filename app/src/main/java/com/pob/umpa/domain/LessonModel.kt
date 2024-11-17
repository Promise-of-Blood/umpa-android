package com.pob.umpa.domain

data class LessonModel(
    val lessonId: String,
    val teacherId: String,
    val title: String,
    val content: String,
    val price: Int,
    val stars: Int,
    val matchingType: String,
    val instrumentsType: String,
    val locationCity: String,
    val locationArea: String
)

