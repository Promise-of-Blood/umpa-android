package com.pob.umpa.domain

import com.pob.umpa.R
import java.time.LocalDateTime

object MockMentoringDetailData {
    val mockMentoringDetailData = MentoringDetailModel(
        mentoringDetailId = "mentoringDetail001",
        mentorId = "mentor001",
        image = R.drawable.ic_launcher_background,
        title = "피아노 레슨",
        content = "내용 사이에 유튜브 링크가 있습니다.\nhttps://www.youtube.com/watch?v=CLnkrGRiRfI\n내용 사이에 유튜브 링크가 있습니다.\nhttps://www.youtube.com/watch?v=CLnkrGRiRfI\n내용 사이에 유튜브 링크가 있습니다.\nhttps://www.youtube.com/watch?v=CLnkrGRiRfI\n내용 사이에 유튜브 링크가 있습니다.\nhttps://www.youtube.com/watch?v=CLnkrGRiRfI\n",
        mentorImage = R.drawable.ic_launcher_background,
        mentorName = "멘토 이름",
        mentorInstrumentsType = "작곡",
        createdAt = LocalDateTime.now(),
        commentList = listOf(
            Comment(
                commentId = "comment001",
                username = "유저 이름",
                content = "댓글 내용",
                createdAt = LocalDateTime.now().minusMinutes(45)
            ),
            Comment(
                commentId = "comment002",
                username = "유저 이름",
                content = "댓글 내용",
                createdAt = LocalDateTime.now().minusHours(5)
            ),
            Comment(
                commentId = "comment003",
                username = "유저 이름",
                content = "댓글 내용",
                createdAt = LocalDateTime.now().minusDays(3)
            ),
            Comment(
                commentId = "comment004",
                username = "유저 이름",
                content = "댓글 내용",
                createdAt = LocalDateTime.now().minusWeeks(2)
            ),
            Comment(
                commentId = "comment005",
                username = "유저 이름",
                content = "댓글 내용",
                createdAt = LocalDateTime.now().minusMonths(6)
            ),
            Comment(
                commentId = "comment006",
                username = "유저 이름",
                content = "댓글 내용",
                createdAt = LocalDateTime.now().minusYears(3)
            ),
        ),
    )
}
