package com.pob.umpa.domain

import android.media.Image
import java.time.LocalDate

class AcceptDetail(
    val id: Int,
    val header: AcceptDetailHeader,
    val content: AcceptDetailContent,
    val mentoTag: List<MentoMainInfo>,
    val commentList: List<Comment>
) {
    companion object {
        private val mockData = listOf(
            AcceptDetail(
                id = 1,
                header = AcceptDetailHeader(
                    title = "호원대 베이스 전공 합격 후기",
                    writerImage = "R.drawable.hingkku",
                    writerName = "이강진",
                    createdAt = LocalDate.now()
                ),
                content = AcceptDetailContent(
                    imageList = listOf("hingkku", "hingkku"),
                    content = "합격 후기 내용입니다. 합격 후기 내용입니다."
                ),
                mentoTag = listOf(
                    MentoMainInfo(
                        profileImage = "hingkku",
                        name = "김현지",
                        rating = 4.5f,
                        majorList = listOf(MajorType.PIANO, MajorType.VOCAL),
                        careerList = listOf("피아노", "작곡")
                    )
                ),
                commentList = listOf(
                    Comment(
                        profileImage = "hingkku",
                        writerName = "김현지",
                        createdAt = LocalDate.now(),
                        content = "모래",
                        isAnonymous = true
                    )
                )
            )
        )

        fun findById(id: Int): AcceptDetail {
            return mockData.find { it.id == id }
                ?: AcceptDetail(
                    id = 0,
                    header = AcceptDetailHeader(
                        title = "제목 데이터 없음",
                        writerImage = "R.drawable.hingkku",
                        writerName = "작성자 정보 없음",
                        createdAt = LocalDate.now()
                    ),
                    content = AcceptDetailContent(
                        imageList = listOf("hingkku", "hingkku"),
                        content = "내용 없음"
                    ),
                    mentoTag = listOf(
                        MentoMainInfo(
                            profileImage = "hingkku",
                            name = "정보 없음",
                            rating = 4.5f,
                            majorList = listOf(MajorType.PIANO, MajorType.VOCAL),
                            careerList = listOf("피아노", "작곡")
                        )
                    ),
                    commentList = listOf(
                        Comment(
                            profileImage = "hingkku",
                            writerName = "김현지",
                            createdAt = LocalDate.now(),
                            content = "모래",
                            isAnonymous = true
                        )
                    )

                )
        }
    }
}

class MentoMainInfo(
    val profileImage: String,
    val name: String,
    val rating: Float,
    val majorList: List<MajorType>,
    val careerList: List<String>
)

enum class MajorType(val label: String) {
    PIANO("피아노"),
    COMPOSITION("작곡"),
    DRUM("드럼"),
    BASS("베이스"),
    GUITAR("기타"),
    VOCAL("보컬"),
    ELECTRONIC_MUSIC("전자음악"),
    WIND_INSTRUMENT("관악");
}

data class AcceptDetailHeader(
    val title: String,
    val writerImage: String,
    val writerName: String,
    val createdAt: LocalDate
)

// 이미지 리스트와 게시글 내용이 들어가는 클래스
data class AcceptDetailContent(
    val imageList: List<String>,
    val content: String
)

data class Comment(
    val profileImage: String,    // 프로필 사진
    val writerName: String,     // 작성자 이름
    val createdAt: LocalDate,   // 작성일
    val content: String,        // 내용
    val isAnonymous: Boolean,   // 익명 여부
)
