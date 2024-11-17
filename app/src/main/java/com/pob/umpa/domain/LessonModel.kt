package com.pob.umpa.domain

data class MatchingModel(
    val lessonId: String,
    val teacherId: String,
    val teacherName: String,
    val title: String,
    val content: String,
    val price: Int,
    val stars: Float,
    val matchingType: String,
    val instrumentsType: String,
    val locationCity: String,
    val locationArea: String
)

