package com.pob.umpa.ui.view.main.home

import com.pob.umpa.R

data class CommunityShortcutItem(
    val name : String,
    val image : Int,
)

val CommunityShortcutItemList = listOf<CommunityShortcutItem>(
    CommunityShortcutItem("입시 후기", R.drawable.community_review),
    CommunityShortcutItem("정보 공유", R.drawable.community_share),
    CommunityShortcutItem("질문 게시판", R.drawable.community_question),
    CommunityShortcutItem("멘토링", R.drawable.community_mentoring),
)