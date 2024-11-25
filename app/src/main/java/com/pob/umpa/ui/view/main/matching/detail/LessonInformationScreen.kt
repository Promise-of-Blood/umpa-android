package com.pob.umpa.ui.view.main.matching.detail

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pob.umpa.domain.LessonDetail
import com.pob.umpa.ui.theme.Typography
import com.pob.umpa.ui.theme.UmpaColor
import java.util.UUID

@Composable
fun LessonInformationScreen(
    lessonDetail: LessonDetail, modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        verticalArrangement = spacedBy(24.dp), modifier = modifier.verticalScroll(scrollState)
    ) {
        IconCardList(
            lessonDetail = lessonDetail, modifier = Modifier.fillMaxWidth()
        )
        TextCard(
            title = "수업 소개",
            content = lessonDetail.introduction,
            modifier = Modifier.fillMaxWidth(),
        )
        TextCard(
            title = "수업 대상",
            content = lessonDetail.studentTarget,
            modifier = Modifier.fillMaxWidth(),
        )
        InformationCard(
            title = "작업실 사진",
            modifier = Modifier.fillMaxWidth(),
        ) {
            ImageSlide(lessonDetail.studioImageList)
        }
    }
}

@Composable
fun IconCardList(
    lessonDetail: LessonDetail, modifier: Modifier = Modifier
) {
    InformationCardWithIcon(
        icon = Icons.Rounded.CalendarToday, modifier = modifier
    ) {
        Text(
            text = lessonDetail.schedule, style = Typography.titleSmall, color = UmpaColor.Black
        )
        Text(
            text = "학생과 조율해서 결정 해요", style = Typography.bodySmall, color = UmpaColor.Grey
        )
    }

    InformationCardWithIcon(
        icon = Icons.Rounded.CalendarToday, modifier = modifier
    ) {
        Text(
            text = lessonDetail.location, style = Typography.titleSmall, color = UmpaColor.Black
        )
        Text(
            text = "*자세한 위치는 레슨 확정 후 공개", style = Typography.bodySmall, color = UmpaColor.Main
        )
    }

    InformationCardWithIcon(
        icon = Icons.Rounded.CalendarToday, modifier = modifier
    ) {
        lessonDetail.lessonStyle.forEach { style ->
            Text(
                text = style.label,
                style = Typography.titleSmall,
                color = UmpaColor.Black,
                lineHeight = 32.sp
            )
        }
    }
}

@Composable
fun TextCard(
    title: String, content: String, modifier: Modifier = Modifier
) {
    InformationCard(
        title = title, modifier = modifier
    ) {
        Text(
            text = content,
            style = Typography.bodySmall,
            color = UmpaColor.Black,
            lineHeight = 24.sp
        )
    }
}

@Composable
fun ImageSlide(
    imageList: List<Int>, modifier: Modifier = Modifier
) {
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
                modifier = Modifier.fillMaxSize()
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
fun IndicatorDot(isSelected: Boolean, modifier: Modifier = Modifier) {
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

@Composable
fun InformationCard(
    title: String, modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    Column(
        verticalArrangement = spacedBy(24.dp), modifier = modifier
            .lightGrayBorder()
            .cardPadding()
    ) {
        Text(text = title, style = Typography.titleMedium)
        content()
    }
}

@Composable
fun InformationCardWithIcon(
    icon: ImageVector, modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = spacedBy(24.dp),
        modifier = modifier
            .lightGrayBorder()
            .cardPadding()
            .height(IntrinsicSize.Max)
    ) {
        Icon(
            imageVector = icon,
            tint = UmpaColor.Black,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )

        Column(modifier = Modifier.weight(1f)) {
            content()
        }
    }
}
