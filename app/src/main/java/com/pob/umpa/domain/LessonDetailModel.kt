package com.pob.umpa.domain

import androidx.annotation.DrawableRes
import java.time.LocalDate

data class LessonDetailModel(
    val lessonSummary: MatchingModel, // 수업 소개 (개요)
    val lessonDetail: LessonDetail, // 수업 소개 (상세)
    val teacherDetail: TeacherDetail, // 선생님 소개
    val curriculumList: List<String>, // 커리큘럼
    val reviewList: List<Review>, // 리뷰
    val successStoryList: List<SuccessStory>, // 합격 후기
)

enum class LessonStyle(val label: String) {
    OFFLINE("비대면 수업"), ONLINE("대면 수업"), TRIAL("무료 시범 레슨")
}

data class LessonDetail(
    val schedule: String, // 수업 일정
    val location: String, // 수업 장소
    val lessonStyle: List<LessonStyle> = listOf(), // 수업 방식
    val introduction: String, // 수업 소개
    val studentTarget: String, // 수업 대상
    @DrawableRes val studioImageList: List<Int> = listOf(), // 작업실 사진
)

data class TeacherDetail(
    @DrawableRes val profileImage: Int,
    val summary: String,
    val name: String,
    val socialLinkList: List<SocialLink>,
    val experienceList: List<String>,
    val introduction: String,
)

data class SocialLink(
    @DrawableRes val icon: Int,
    val link: String,
)

data class Review(
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

data class SuccessStory(
    val successStoryId: String,
    val studentId: String,
    val studentName: String,
    val instrumentsType: String, // 악기 종류
    val title: String, // 합격 후기 제목
    val createdAt: LocalDate, // 합격 후기 작성일
    val succeedUniversityList: List<String>, // 합격 대학 목록
    @DrawableRes val certificateImage: Int, // 대학 합격증
)
