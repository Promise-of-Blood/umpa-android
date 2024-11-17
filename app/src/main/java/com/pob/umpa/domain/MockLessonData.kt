package com.pob.umpa.domain

object MockLessonData {
    val mockLessonData = listOf(
        LessonModel(
            lessonId = "lesson001",
            teacherId = "teacher001",
            title = "피아노 반주 레슨",
            content = "경험 많은 선생님과 함께 피아노 반주를 배워보세요.",
            price = 30000,
            stars = 5,
            matchingType = "레슨",
            instrumentsType = "피아노",
            locationCity = "서울",
            locationArea = "강남"
        ),
        LessonModel(
            lessonId = "lesson002",
            teacherId = "teacher002",
            title = "작곡 레슨과 악보 제작",
            content = "작곡 기법과 악보 제작 스킬을 배우는 고급 레슨입니다.",
            price = 45000,
            stars = 4,
            matchingType = "악보제작",
            instrumentsType = "작곡",
            locationCity = "서울",
            locationArea = "홍대"
        ),
        LessonModel(
            lessonId = "lesson003",
            teacherId = "teacher003",
            title = "드럼 퍼포먼스 레슨",
            content = "초급부터 고급까지 드럼 연주를 완벽히 마스터해보세요.",
            price = 40000,
            stars = 5,
            matchingType = "레슨",
            instrumentsType = "드럼",
            locationCity = "부산",
            locationArea = "해운대"
        ),
        LessonModel(
            lessonId = "lesson004",
            teacherId = "teacher004",
            title = "베이스 라인 제작 레슨",
            content = "베이스 연주의 기본부터 고급 테크닉까지 배워보세요.",
            price = 38000,
            stars = 4,
            matchingType = "레슨",
            instrumentsType = "베이스",
            locationCity = "인천",
            locationArea = "송도"
        ),
        LessonModel(
            lessonId = "lesson005",
            teacherId = "teacher005",
            title = "기타 반주 레슨",
            content = "기타 반주를 배우고 연주 실력을 키워보세요.",
            price = 35000,
            stars = 4,
            matchingType = "레슨",
            instrumentsType = "기타",
            locationCity = "서울",
            locationArea = "이태원"
        ),
        LessonModel(
            lessonId = "lesson006",
            teacherId = "teacher006",
            title = "보컬 트레이닝 레슨",
            content = "보컬 퍼포먼스와 발성 기법을 배우는 레슨입니다.",
            price = 40000,
            stars = 5,
            matchingType = "레슨",
            instrumentsType = "보컬",
            locationCity = "대구",
            locationArea = "동성로"
        ),
        LessonModel(
            lessonId = "lesson007",
            teacherId = "teacher007",
            title = "전자음악 제작과 믹싱",
            content = "전자음악 제작, 믹싱, 마스터링까지 배울 수 있습니다.",
            price = 50000,
            stars = 5,
            matchingType = "악보제작",
            instrumentsType = "전자음악",
            locationCity = "서울",
            locationArea = "신촌"
        )
    )

}