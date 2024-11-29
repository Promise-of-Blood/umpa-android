package com.pob.umpa.ui.view.main.matching.detail.component.card

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun VideoPlayerListCard(videoList: List<String>, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        videoList.forEachIndexed { index, video ->
            // TODO: Youtube Player
        }
    }
}
