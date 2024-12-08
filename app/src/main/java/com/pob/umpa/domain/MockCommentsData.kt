package com.pob.umpa.domain

object MockCommentsData {
    val mockCommentsData = listOf(
        QuestionCommentModel(
            isAnonymous = true,
            author = "익명의 사용자",
            content = "댓글 내용11",
            dateCreated = "1일전"
        ),
        QuestionCommentModel(
            isAnonymous = true,
            author = "익명의 사용자",
            content = "댓글 내용11",
            dateCreated = "2일전"
        ),
        QuestionCommentModel(
            isAnonymous = true,
            author = "익명의 사용자",
            content = "댓글 내용11",
            dateCreated = "3일전"
        ),
    )
}