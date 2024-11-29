package com.pob.umpa.ui.view.main.matching.detail.component.card

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun VideoPlayerListCard(videoList: List<String>, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        videoList.forEachIndexed { index, video ->
            YoutubePlayer(videoId = video.split("=")[1], modifier = Modifier)
        }
    }
}

@Composable
fun YoutubePlayer(
    videoId: String, modifier: Modifier
) {
    Box(modifier = modifier) {
        AndroidView(factory = { context ->
            YouTubePlayerView(context).apply {
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                })
            }
        })
    }
}
