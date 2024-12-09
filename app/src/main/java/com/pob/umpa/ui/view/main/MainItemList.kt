package com.pob.umpa.ui.view.main

import com.pob.umpa.R

val MainItemList = listOf(
    MainNavItem(
        topTitle = "Umpa",
        name = "홈",
        route = "home",
        icon = R.drawable.baseline_home_24
    ),
    MainNavItem(
        topTitle = "매칭서비스",
        name = "매칭서비스",
        route = "matching",
//        route = "calendar",
        icon = R.drawable.baseline_matching_24
    ),
    MainNavItem(
        topTitle = "커뮤니티",
        name = "커뮤니티",
        route = "community",
        icon = R.drawable.baseline_community_24
    ),
    MainNavItem(
        topTitle = "채팅",
        name = "채팅",
        route = "chatting",
        icon = R.drawable.baseline_chat_24
    ),
    MainNavItem(
        topTitle = "내 정보",
        name = "내 정보",
        route = "myinfo",
        icon = R.drawable.baseline_myinfo_24
    ),
)