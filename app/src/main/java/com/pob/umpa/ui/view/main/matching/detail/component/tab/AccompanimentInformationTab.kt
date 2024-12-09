package com.pob.umpa.ui.view.main.matching.detail.component.tab

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.rounded.Album
import androidx.compose.material.icons.rounded.FolderOpen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pob.umpa.domain.AccompanimentInformationTabItem
import com.pob.umpa.ui.view.main.matching.detail.component.card.Card
import com.pob.umpa.ui.view.main.matching.detail.component.card.IconCard

@Composable
fun AccompanimentInformationTab(
    data: AccompanimentInformationTabItem,
    modifier: Modifier = Modifier,
) = with(data) {
    Column(
        verticalArrangement = spacedBy(24.dp),
        modifier = modifier,
    ) {
        IconCard(
            icon = Icons.Rounded.Album,
            title = "연습",
            description = practice,
        )
        IconCard(
            icon = Icons.Rounded.FolderOpen,
            title = "MR(녹음본) 제공",
            description = mrProvide,
        )
        IconCard(
            icon = Icons.Outlined.LocationOn,
            title = "연습 위치",
            description = practiceLocation,
        )
        Card(
            title = "반주 소개",
            description = accompanimentInformation,
        )
    }
}
