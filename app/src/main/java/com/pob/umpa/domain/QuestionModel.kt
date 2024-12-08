package com.pob.umpa.domain

// 질문 모델
data class QuestionModel(
    val isAnonymous: Boolean,       // 익명 여부
    val author: String,             // 작성자
    val dateCreated: String,        // 작성일자
    val content: String,            // 질문 내용
    val comments: List<QuestionCommentModel> // 댓글 리스트
)

// 댓글 모델
data class QuestionCommentModel(
    val isAnonymous: Boolean,       // 익명 여부
    val author: String,             // 작성자
    val content: String,            // 댓글 내용
    val dateCreated: String         // 작성일자
)
