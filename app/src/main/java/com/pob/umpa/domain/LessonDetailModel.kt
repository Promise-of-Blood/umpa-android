package com.pob.umpa.domain

import androidx.annotation.DrawableRes
import java.time.LocalDate

data class MatchingDetailModel(
    val summary: MatchingModel,
    val tabList: List<Pair<MatchingDetailTabType, MatchingDetailTabItem>>,
)

enum class MatchingDetailTabType(val label: String) {
    TEACHER_INFORMATION("선생님 소개"), LESSON_INFORMATION("수업 소개"), SERVICE_INFORMATION("서비스 안내"), ACCOMPANIMENT_INFORMATION(
        "반주 정보"
    ),
    CURRICULUM("커리큘럼"), SAMPLE_CHECK("샘플 확인"), REVIEW("리뷰"),
}

/*
* MatchingDetailTabItem
* */
sealed interface MatchingDetailTabItem

sealed interface MatchingDetailSampleCheckTabItem

data class ReviewTabItem(
    val reviewList: List<ReviewItem>, // 리뷰
    val successStoryList: List<SuccessStoryItem>, // 합격 후기
) : MatchingDetailTabItem

data class LessonInformationTabItem(
    val schedule: String, // 수업 일정
    val location: String, // 수업 장소
    val lessonStyle: List<LessonStyle> = listOf(), // 수업 방식
    val introduction: String, // 수업 소개
    val studentTarget: String, // 수업 대상
    @DrawableRes val studioImageList: List<Int> = listOf(), // 작업실 사진
) : MatchingDetailTabItem

data class CurriculumTabItem(
    val curriculumList: List<String>,
) : MatchingDetailTabItem

data class SampleScoreTabItem(
    @DrawableRes val imageList: List<Int>, // 샘플 악보
) : MatchingDetailTabItem, MatchingDetailSampleCheckTabItem

data class SampleMRTabItem(
    val mrList: List<String>, // 샘플 MR 영상 url (Youtube)
) : MatchingDetailTabItem, MatchingDetailSampleCheckTabItem

data class ServiceInformationTabItem(
    val price: List<Pair<String, Int>>, // 가격 안내
    val averageTimeCost: String, // 평균 소요 시간
    val lessonCount: String, // 수업 횟수
    val usageProgram: String, // 사용 프로그램
    val serviceInformation: String, // 서비스 소개
) : MatchingDetailTabItem

data class AccompanimentInformationTabItem(
    val practice: String, // 연습
    val mrProvide: String, // MR 제공
    val practiceLocation: String, // 연습 위치
    val accompanimentInformation: String, // 반주 소개
) : MatchingDetailTabItem

data class TeacherInformationTabItem(
    @DrawableRes val profileImage: Int,
    val summary: String,
    val name: String,
    val socialLinkList: List<SocialLink>,
    val experienceList: List<String>,
    val introduction: String,
) : MatchingDetailTabItem

/*
* etc.
* */
data class ReviewItem(
    val reviewId: String,
    val studentId: String,
    val studentName: String,
    @DrawableRes val studentProfileImage: Int, // 프로필 사진
    val instrumentsType: String, // 악기 종류
    val stars: Float, // 별점
    val createdAt: LocalDate, // 리뷰 작성일
    val lessonPeriod: String, // 레슨 기간
    val content: String, // 리뷰 내용
    @DrawableRes val contentImageList: List<Int> = listOf(), // 리뷰 첨부 이미지
)

data class SuccessStoryItem(
    val successStoryId: String,
    val studentId: String,
    val studentName: String,
    val instrumentsType: String, // 악기 종류
    val title: String, // 합격 후기 제목
    val createdAt: LocalDate, // 합격 후기 작성일
    val succeedUniversityList: List<String>, // 합격 대학 목록
    @DrawableRes val certificateImage: Int, // 대학 합격증
)

data class SocialLink(
    @DrawableRes val icon: Int,
    val link: String,
)

enum class LessonStyle(val label: String) {
    OFFLINE("비대면 수업"), ONLINE("대면 수업"), TRIAL("무료 시범 레슨")
}
