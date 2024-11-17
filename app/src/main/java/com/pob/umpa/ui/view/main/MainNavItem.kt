package com.pob.umpa.ui.view.main

data class MainNavItem (
    val topTitle : String,
    val name : String,
    val route : String,
    val icon : Int,
    val badgeCount : Int = 0,
)