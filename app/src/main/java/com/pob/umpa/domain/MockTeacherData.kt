package com.pob.umpa.domain

import com.pob.umpa.R

object MockTeacherData {
    val mockTeacherData: Teacher = Teacher(
        profileImage = R.drawable.ic_launcher_background,
        summary = "서울예대 동아방송대를 작곡으로 붙고\n" + "호원대를 베이스로 붙은 사람",
        name = "김현지",
        socialLinks = listOf(
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
        experiences = listOf(
            "서울예술대학교 작곡 전공 졸업", "동아방송대 작곡 전공 합격", "호원대 베이스 전공 합격", "전 LNS, SMMA 출강"
        ),
        introduce = "저는 세상에서 제일 게으른 사람입니다.\n" + "근데 너네는 힝꾸 없잖아. 어쩔티비 알파카좋아 오리너구리도 좋아 담배는 맛있어 오늘 뭐먹지 쓰레기버리러 나가기 귀찮다.\n" + "\n" + "음 그래서 저한테 레슨을 받으신다면 최선을 다해 가르쳐드리겠습니다.\n" + "아무튼 그렇습니다. 게으르긴 하지만 책임감은 있는 편이라서 돈 받은 값은 합니다.\n" + "네네치킨"
    )
}
