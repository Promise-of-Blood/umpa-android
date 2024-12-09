package com.pob.umpa.ui.view.main.community.AcceptReview

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.pob.umpa.R
import com.pob.umpa.domain.AcceptDetail
import com.pob.umpa.domain.AcceptDetailContent
import com.pob.umpa.domain.AcceptDetailHeader
import com.pob.umpa.domain.MentoMainInfo
import com.pob.umpa.ui.theme.pretendardFontFamily
import java.time.LocalDate

@Composable
fun AcceptReviewDetailScreen(acceptDetailId: Int) {
    val acceptDetail = AcceptDetail.findById(id = acceptDetailId)
    Box(
        modifier = Modifier
    ) {
        LazyColumn(
            modifier = Modifier
                .padding()
        ) {
            item {
                AcceptReviewHeader(header = acceptDetail.header)
            }
            item {
                AcceptReviewContent(content = acceptDetail.content)
            }
            item {
                AcceptReviewMentoTag(mentoTag = acceptDetail.mentoTag)
            }
        }
    }
}

@Composable
fun AcceptReviewMentoTag(mentoTag: List<MentoMainInfo>) {

}

@Composable
fun AcceptReviewContent(content: AcceptDetailContent) {
    val pagerState = rememberPagerState(initialPage = 0,
        pageCount = { content.imageList.size} )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp) // 전체 패딩
    ) {
        // 가로 페이저로 이미지 표시
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .aspectRatio(16 / 9f) // 화면 비율 조정
            ) {
                val drawableName = content.imageList[page].removePrefix("R.drawable.")
                val drawableId = R.drawable::class.java.getField(drawableName).getInt(null)
                Image(
                    painter = painterResource(id = drawableId),
                    contentDescription = "이미지 $page",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp)) // 둥근 모서리
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp)) // 이미지 리스트와 텍스트 간 간격

        // Content 텍스트
        Text(
            text = content.content,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}

@Composable
fun AcceptReviewHeader(header: AcceptDetailHeader) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp) // 전체 패딩 추가
    ) {
        // 제목
        Text(
            text = "합격후기",
            style = TextStyle(fontSize = 18.sp) // 스타일 예시
        )
        Spacer(modifier = Modifier.height(8.dp)) // 제목과 제목 사이 간격
        Text(
            text = header.title,
            style = TextStyle(fontSize = 20.sp)
        )
        Spacer(modifier = Modifier.height(16.dp)) // 제목과 Row 간 간격

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically // 수직 정렬 설정
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically // 내부 Row 수직 정렬
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hingkku), // 프로필 이미지 리소스 ID
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp) // 이미지 크기 설정
                        .clip(CircleShape) // 원형으로 자르기
                        .border(1.dp, Color.Transparent, CircleShape) // 테두리 추가
                )
                Spacer(modifier = Modifier.width(8.dp)) // 이미지와 텍스트 간 간격
                Text(
                    text = header.writerName,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = pretendardFontFamily // Pretendard 폰트
                    )
                )
            }
            Text(
                text = header.createdAt.toString(),
                style = TextStyle(fontSize = 14.sp) // 날짜 텍스트 스타일
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AcceptReviewDetailScreenPreview() {
    AcceptReviewDetailScreen(acceptDetailId = 0)
}
