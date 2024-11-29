package com.pob.umpa.ui.view.main.matching.detail.component.tab

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.rounded.AutoStories
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pob.umpa.domain.LessonInformationTabItem
import com.pob.umpa.ui.view.main.matching.detail.component.card.Card
import com.pob.umpa.ui.view.main.matching.detail.component.card.IconCard
import com.pob.umpa.ui.view.main.matching.detail.component.card.ImageSlideCard

@Composable
fun LessonInformationTab(
    data: LessonInformationTabItem,
    modifier: Modifier = Modifier,
) = with(data) {
    Column(
        verticalArrangement = spacedBy(24.dp),
        modifier = modifier,
    ) {
        IconCard(
            icon = Icons.Rounded.CalendarToday,
            title = schedule,
            description = "학생과 조율해서 결정 해요",
        )
        IconCard(
            icon = Icons.Outlined.LocationOn,
            title = location,
            label = "*자세한 위치는 레슨 확정 후 공개",
        )
        IconCard(
            icon = Icons.Rounded.AutoStories,
            titleList = lessonStyle.map { it.label },
            alignment = Alignment.Top,
        )
        Card(
            title = "수업 소개",
            description = introduction,
        )
        Card(
            title = "수업 대상",
            description = studentTarget,
        )
        ImageSlideCard(
            title = "작업실 사진",
            imageList = studioImageList,
        )
    }
}
