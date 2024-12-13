package com.pob.umpa.ui.view.main.community.Mentoring

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pob.umpa.R

class ArticleThumbnail(
    val image: Int,
    val title: String
)

@Composable
fun MentoringScreen() {
    val articleList = listOf(
        ArticleThumbnail(R.drawable.hingkku, "제목 입력 제목 입력 제목 입력 제목 입력"),
        ArticleThumbnail(R.drawable.hingkku, "제목 입력 제목 입력 제목 입력 제목 입력"),
        ArticleThumbnail(R.drawable.hingkku, "제목 입력 제목 입력 제목 입력 제목 입력"),
        ArticleThumbnail(R.drawable.hingkku, "제목 입력 제목 입력 제목 입력 제목 입력"),
        ArticleThumbnail(R.drawable.hingkku, "제목 입력 제목 입력 제목 입력 제목 입력"),
        ArticleThumbnail(R.drawable.hingkku, "제목 입력 제목 입력 제목 입력 제목 입력"),
        ArticleThumbnail(R.drawable.hingkku, "제목 입력 제목 입력 제목 입력 제목 입력"),
        ArticleThumbnail(R.drawable.hingkku, "제목 입력 제목 입력 제목 입력 제목 입력"),
        ArticleThumbnail(R.drawable.hingkku, "제목 입력 제목 입력 제목 입력 제목 입력"),
        ArticleThumbnail(R.drawable.hingkku, "제목 입력 제목 입력 제목 입력 제목 입력"),
        ArticleThumbnail(R.drawable.hingkku, "제목 입력 제목 입력 제목 입력 제목 입력"),
        ArticleThumbnail(R.drawable.hingkku, "제목 입력 제목 입력 제목 입력 제목 입력"),
        ArticleThumbnail(R.drawable.hingkku, "제목 입력 제목 입력 제목 입력 제목 입력"),
        ArticleThumbnail(R.drawable.hingkku, "제목 입력 제목 입력 제목 입력 제목 입력"),
        ArticleThumbnail(R.drawable.hingkku, "제목 입력 제목 입력 제목 입력 제목 입력"),
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 2개의 열
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp), // 그리드의 외부 여백
        verticalArrangement = Arrangement.spacedBy(8.dp), // 아이템 간 수직 간격
        horizontalArrangement = Arrangement.spacedBy(8.dp) // 아이템 간 가로 간격
    ) {
        items(articleList) { article ->
            ArticleThumbnail(article)
        }
    }
}


@Composable
fun ArticleThumbnail(article: ArticleThumbnail) {
    Column {
        Image(
            painter = painterResource(id = article.image),
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(1.3f) // 정사각형 비율 유지
                .clip(RoundedCornerShape(8.dp)) // 모서리 둥글게
                .fillMaxWidth(),
            contentScale = ContentScale.Crop // 이미지 크롭하여 채우기
        )
        Text(article.title)
    }
}


@Preview(showBackground = true)
@Composable
fun MentoringScreenPreview() {
    MentoringScreen()
}