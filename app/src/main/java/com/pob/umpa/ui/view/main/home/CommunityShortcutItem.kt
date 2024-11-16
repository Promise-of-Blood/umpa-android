package com.pob.umpa.ui.view.main.home

data class CommunityShortcutItem(
    val name : String,
    val image : Int,
)

val CommunityShortcutItemList = listOf<CommunityShortcutItem>(
    CommunityShortcutItem("입시 후기", 0),
    CommunityShortcutItem("정보 공유", 0),
    CommunityShortcutItem("질문 게시판", 0),
    CommunityShortcutItem("멘토링", 0),
)