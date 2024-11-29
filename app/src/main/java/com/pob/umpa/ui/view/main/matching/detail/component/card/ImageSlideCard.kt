package com.pob.umpa.ui.view.main.matching.detail.component.card

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pob.umpa.ui.theme.UmpaColor
import java.util.UUID

@Composable
fun ImageSlideCard(title: String, imageList: List<Int>, modifier: Modifier = Modifier) {
    Card(
        title = title,
        modifier = modifier,
    ) {
        ImageSlide(imageList)
    }
}

@Composable
private fun ImageSlide(imageList: List<Int>, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(pageCount = { imageList.size })

    Column(
        verticalArrangement = spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fill,
            key = { UUID.randomUUID().toString() },
        ) { index ->
            Image(
                painter = painterResource(imageList[index]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
            )
        }

        Row(
            horizontalArrangement = spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            repeat(imageList.size) {
                IndicatorDot(isSelected = it == pagerState.currentPage)
            }
        }
    }
}

@Composable
private fun IndicatorDot(isSelected: Boolean, modifier: Modifier = Modifier) {
    val animatedColor by animateColorAsState(
        targetValue = if (isSelected) UmpaColor.Terri else UmpaColor.LightGray, label = ""
    )

    Box(
        modifier = modifier
            .padding(2.dp)
            .size(10.dp)
            .clip(CircleShape)
            .background(animatedColor)
    )
}
