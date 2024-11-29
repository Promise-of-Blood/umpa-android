package com.pob.umpa.domain

import com.pob.umpa.R
import com.pob.umpa.domain.MockLessonData.mockLessonData
import java.time.LocalDate

object MockLessonDetailData {
    private val reviewList = listOf(
        ReviewItem(
            reviewId = "review001",
            studentId = "student001",
            studentName = "전자담배뚜껑따기",
            studentProfileImage = R.drawable.baseline_myinfo_24,
            instrumentsType = "작곡",
            stars = 5.0f,
            createdAt = LocalDate.of(2024, 11, 19),
            lessonPeriod = "1년 이상",
            content = "선생님한테 정말 많은 걸 배웠습니다.\n" + "그냥 음악적인 지식 뿐만 아니라 연습하는 루틴까지 체크해주시고 열정적으로 알려주셨습니다.\n" + "의지 박약인 저를 합격 할 수 있게 해주신 은인 이십니당..",
            contentImageList = listOf(
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
            )
        ), ReviewItem(
            reviewId = "review002",
            studentId = "student002",
            studentName = "락토핏 골드",
            studentProfileImage = R.drawable.baseline_myinfo_24,
            instrumentsType = "작곡",
            stars = 5.0f,
            createdAt = LocalDate.of(2024, 10, 11),
            lessonPeriod = "3-6개월",
            content = "개인 사정으로 잠깐 그만 두었지만 정말 좋은 선생님 이십니다.\n" + "빡센 선생님 찾고 있었는데 정말 빡세게 알려주십니다.\n" + "의지 박약인 사람한테 추천드려요",
            contentImageList = listOf()
        )
    )

    private val successStoryList = listOf(
        SuccessStoryItem(
            successStoryId = "successStory001",
            studentId = "student001",
            studentName = "우도왕자",
            instrumentsType = "작곡",
            title = "서울예대 작곡 합격 후기",
            createdAt = LocalDate.of(2024, 11, 19),
            succeedUniversityList = listOf("서울예술대학교"),
            certificateImage = R.drawable.ic_launcher_background
        ),
        SuccessStoryItem(
            successStoryId = "successStory002",
            studentId = "student002",
            studentName = "김태영일이삼",
            instrumentsType = "작곡",
            title = "호원대 작곡 합격 했습니다",
            createdAt = LocalDate.of(2024, 11, 19),
            succeedUniversityList = listOf("호원대학교", "국제예술대학교", "머시깽이대학교"),
            certificateImage = R.drawable.ic_launcher_background
        ),
    )

    // 작곡
    val mockComposingLessonDetailData = MatchingDetailModel(
        summary = mockLessonData.random(),
        tabList = listOf(
            MatchingDetailTabType.TEACHER_INFORMATION to TeacherInformationTabItem(
                profileImage = R.drawable.ic_launcher_background,
                summary = "서울예대 동아방송대를 작곡으로 붙고\n" + "호원대를 베이스로 붙은 사람",
                name = "김현지",
                socialLinkList = listOf(
                    SocialLink(
                        R.drawable.ic_youtube, ""
                    ),
                    SocialLink(
                        R.drawable.ic_instagram, ""
                    ),
                    SocialLink(
                        R.drawable.ic_naver_blog, ""
                    ),
                ),
                experienceList = listOf(
                    "서울예술대학교 작곡 전공 졸업", "동아방송대 작곡 전공 합격", "호원대 베이스 전공 합격", "전 LNS, SMMA 출강"
                ),
                introduction = "저는 세상에서 제일 게으른 사람입니다.\n" + "근데 너네는 힝꾸 없잖아. 어쩔티비 알파카좋아 오리너구리도 좋아 담배는 맛있어 오늘 뭐먹지 쓰레기버리러 나가기 귀찮다.\n" + "\n" + "음 그래서 저한테 레슨을 받으신다면 최선을 다해 가르쳐드리겠습니다.\n" + "아무튼 그렇습니다. 게으르긴 하지만 책임감은 있는 편이라서 돈 받은 값은 합니다.\n" + "네네치킨"
            ), MatchingDetailTabType.LESSON_INFORMATION to LessonInformationTabItem(
                schedule = "협의 가능",
                location = "서울 연남동",
                lessonStyle = listOf(
                    LessonStyle.OFFLINE, LessonStyle.ONLINE, LessonStyle.TRIAL
                ),
                introduction = "기초적인 음악 이론부터 \n" + "미디 큐베이스 프로툴 로직 에이블톤 활용등 실용음악적으로 곡 쓰기\n" + "클래식적인 오케스트레이션\n" + "힙합 R&B를 접목해 여러가지 리듬 접목해 곡쓰기\n" + "발라드 동요 영상에 음악을 입히는 방법 \n" + "\n" + "Music in Music out 기법부터시작해\n" + "기본적인 화성학 을 토대로전통화성학과 재즈화성학의 비슷한점을 찾고 그 차이점을 알아보고\n" + "컴파운드 하모니를 이용한 새로운 사운드 만들기\n" + "\n" + "관악기나 현악에서만 쓰이는 보이싱을 이용해 피아노 보이싱 연구해보기 등\n" + "관악에서는 피아노 보이싱에 대한 보이싱을 사용할수있는지 에 관한 레슨을 같이 해봅니다\n" + "\n" + "기존의 4마디패턴으로 곡쓰기 론도형식의 반복되는 음악에서 그 반대의 형식까지 \n" + "음악에서 형식은 왜 중요하고 꼭 쓰지않아도 되는것인가\n" + "제가 연구하고 가진 정보와 지식들을 모두 알려드립니다\n",
                studentTarget = "아무리 해도 기본기가 부족하다고 느껴지는 학생\n" + "코드초견 어떻게 해야하는지 모르겠다 하는 학생",
                studioImageList = listOf(
                    R.drawable.ic_launcher_background,
                    R.drawable.ic_launcher_background,
                    R.drawable.ic_launcher_background
                )
            ), MatchingDetailTabType.CURRICULUM to CurriculumTabItem(
                listOf(
                    "7th chord 2-5-1 A form B form",
                    "Major Scale / Minor Scale",
                    "ChordTone Solo",
                    "Blues Scale",
                    "Walking Bass",
                    "Walking Bass + Comping",
                    "Jazz Ballad",
                    "Jazz Ballad 2",
                )
            ), MatchingDetailTabType.REVIEW to ReviewTabItem(
                reviewList = reviewList, successStoryList = successStoryList
            )
        ),
    )

    // 악보 제작
    val mockScoreProductionLessonDetailData = MatchingDetailModel(
        summary = mockLessonData.random(),
        tabList = listOf(
            MatchingDetailTabType.TEACHER_INFORMATION to TeacherInformationTabItem(
                profileImage = R.drawable.ic_launcher_background,
                summary = "악보 제작 선생님 소개 요약",
                name = "악보 제작 선생님 이름",
                socialLinkList = listOf(
                    SocialLink(
                        R.drawable.ic_youtube, ""
                    ),
                    SocialLink(
                        R.drawable.ic_instagram, ""
                    ),
                    SocialLink(
                        R.drawable.ic_naver_blog, ""
                    ),
                ),
                experienceList = listOf(
                    "첫 번째 경력", "두 번째 경력", "세 번째 경력", "네 번째 경력"
                ),
                introduction = "악보 제작 선생님 소개글"
            ),
            MatchingDetailTabType.SERVICE_INFORMATION to ServiceInformationTabItem(
                price = listOf(
                    "보컬" to 20000,
                    "피아노" to 40000,
                    "드럼" to 20000,
                    "베이스" to 30000,
                ),
                averageTimeCost = "평균 소요 시간", // 평균 소요 시간
                lessonCount = "수업 횟수", // 수업 횟수
                usageProgram = "사용 프로그램", // 사용 프로그램
                serviceInformation = "서비스 소개", // 서비스 소개
            ),
            MatchingDetailTabType.SAMPLE_CHECK to SampleScoreTabItem(
                listOf(
                    R.drawable.ic_launcher_background,
                    R.drawable.ic_launcher_background,
                    R.drawable.ic_launcher_background,
                )
            ),
            MatchingDetailTabType.REVIEW to ReviewTabItem(
                reviewList = reviewList, successStoryList = successStoryList
            ),
        ),
    )

    // 반주자
    val mockAccompanimentLessonDetailData = MatchingDetailModel(
        summary = mockLessonData.random(),
        tabList = listOf(
            MatchingDetailTabType.TEACHER_INFORMATION to TeacherInformationTabItem(
                profileImage = R.drawable.ic_launcher_background,
                summary = "반주자 소개 요약",
                name = "반주자 이름",
                socialLinkList = listOf(
                    SocialLink(
                        R.drawable.ic_youtube, ""
                    ),
                    SocialLink(
                        R.drawable.ic_instagram, ""
                    ),
                    SocialLink(
                        R.drawable.ic_naver_blog, ""
                    ),
                ),
                experienceList = listOf(
                    "첫 번째 경력", "두 번째 경력", "세 번째 경력", "네 번째 경력"
                ),
                introduction = "반주자 소개글"
            ),
            MatchingDetailTabType.ACCOMPANIMENT_INFORMATION to AccompanimentInformationTabItem(
                practice = "연습", // 연습
                mrProvide = "MR 제공", // MR 제공
                practiceLocation = "연습 위치", // 연습 위치
                accompanimentInformation = "반주 소개", // 반주 소개
            ),
            MatchingDetailTabType.REVIEW to ReviewTabItem(
                reviewList = reviewList, successStoryList = successStoryList
            ),
        ),
    )

    // MR 제작
    val mockMrProductionLessonDetailData = MatchingDetailModel(
        summary = mockLessonData.random(),
        tabList = listOf(
            MatchingDetailTabType.TEACHER_INFORMATION to TeacherInformationTabItem(
                profileImage = R.drawable.ic_launcher_background,
                summary = "MR 제작 선생님 소개 요약",
                name = "MR 제작 선생님 이름",
                socialLinkList = listOf(
                    SocialLink(
                        R.drawable.ic_youtube, ""
                    ),
                    SocialLink(
                        R.drawable.ic_instagram, ""
                    ),
                    SocialLink(
                        R.drawable.ic_naver_blog, ""
                    ),
                ),
                experienceList = listOf(
                    "첫 번째 경력", "두 번째 경력", "세 번째 경력", "네 번째 경력"
                ),
                introduction = "MR 제작 선생님 소개글"
            ),
            MatchingDetailTabType.SERVICE_INFORMATION to ServiceInformationTabItem(
                price = listOf(
                    "보컬" to 20000,
                    "피아노" to 40000,
                    "드럼" to 20000,
                    "베이스" to 30000,
                ),
                averageTimeCost = "평균 소요 시간", // 평균 소요 시간
                lessonCount = "수업 횟수", // 수업 횟수
                usageProgram = "사용 프로그램", // 사용 프로그램
                serviceInformation = "서비스 소개", // 서비스 소개
            ),
            MatchingDetailTabType.SAMPLE_CHECK to SampleMRTabItem(
                listOf(
                    "https://www.youtube.com/watch?v=_cmhRQDaO80",
                    "https://www.youtube.com/watch?v=_cmhRQDaO80",
                    "https://www.youtube.com/watch?v=_cmhRQDaO80",
                )
            ),
            MatchingDetailTabType.REVIEW to ReviewTabItem(
                reviewList = reviewList, successStoryList = successStoryList
            ),
        ),
    )
}
