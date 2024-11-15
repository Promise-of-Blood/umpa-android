package com.pob.umpa

import androidx.compose.ui.graphics.vector.ImageVector

data class MainNavItem (
    val name : String,
    val route : String,
    val icon : Int,
    val badgeCount : Int = 0,
)