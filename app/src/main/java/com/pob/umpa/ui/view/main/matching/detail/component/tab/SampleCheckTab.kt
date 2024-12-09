package com.pob.umpa.ui.view.main.matching.detail.component.tab

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pob.umpa.domain.MatchingDetailSampleCheckTabItem
import com.pob.umpa.domain.SampleMRTabItem
import com.pob.umpa.domain.SampleScoreTabItem
import com.pob.umpa.ui.view.main.matching.detail.component.card.VideoPlayerListCard
import com.pob.umpa.ui.view.main.matching.detail.component.card.WatermarkImageListCard

@Composable
fun SampleCheckTab(
    data: MatchingDetailSampleCheckTabItem,
    modifier: Modifier = Modifier,
) {
    when (data) {
        is SampleMRTabItem -> VideoPlayerListCard(data.mrList, modifier)
        is SampleScoreTabItem -> WatermarkImageListCard(data.imageList, modifier)
    }
}
