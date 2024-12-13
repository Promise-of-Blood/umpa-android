package com.pob.umpa.domain

import androidx.annotation.DrawableRes
import java.time.LocalDateTime

data class MentoringDetailModel(
    val mentoringDetailId: String,
    val mentorId: String,
    @DrawableRes val image: Int,
    val title: String,
    val content: String,
    @DrawableRes val mentorImage: Int,
    val mentorName: String,
    val mentorInstrumentsType: String,
    val createdAt: LocalDateTime,
    val commentList: List<DetailComment>,
)

data class DetailComment(
    val commentId: String,
    val username: String,
    val content: String,
    val createdAt: LocalDateTime,
)
