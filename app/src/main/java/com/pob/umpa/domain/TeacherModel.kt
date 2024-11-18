package com.pob.umpa.domain

import androidx.annotation.DrawableRes

data class Teacher(
    @DrawableRes val profileImage: Int,
    val summary: String,
    val name: String,
    val socialLinks: List<SocialLink>,
    val experiences: List<String>,
    val introduce: String,
)

data class SocialLink(
    @DrawableRes val icon: Int,
    val link: String,
)
