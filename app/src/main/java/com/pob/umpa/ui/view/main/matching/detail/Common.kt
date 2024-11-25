package com.pob.umpa.ui.view.main.matching.detail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pob.umpa.ui.theme.UmpaColor

fun Modifier.lightGrayBorder(): Modifier = this.border(
    width = 1.dp,
    color = UmpaColor.LightGray,
    shape = RoundedCornerShape(8.dp),
)

fun Modifier.cardPadding() = this.padding(
    horizontal = 16.dp,
    vertical = 24.dp,
)
