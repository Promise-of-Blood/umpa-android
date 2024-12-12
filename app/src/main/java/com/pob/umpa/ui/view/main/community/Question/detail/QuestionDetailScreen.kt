package com.pob.umpa.ui.view.main.community.Question.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pob.umpa.R
import com.pob.umpa.domain.MockCommentsData
import com.pob.umpa.domain.QuestionCommentModel
import com.pob.umpa.ui.theme.Typography
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.theme.UmpaTheme

@Composable
fun QuestionDetailScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                TopTitle(modifier = Modifier.fillMaxWidth())

                UserProfile(modifier = Modifier)

                Spacer(modifier = Modifier.height(30.dp))

                QuestionContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(100.dp)
                )

                HorizontalDivider(color = UmpaColor.LightGray, thickness = 1.dp)

                Spacer(modifier = Modifier.height(30.dp))
            }

            commentList(MockCommentsData.mockCommentsData)
        }

        QuestionCommentInput()
    }
}

@Composable
fun QuestionCommentInput() {
    TODO("Not yet implemented")
}

@Composable
fun TopTitle(modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "질문",
            style = Typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = UmpaColor.Black,
        )

        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "Menu",
            )
        }
    }
}

@Composable
fun UserProfile(modifier: Modifier) {
    Row(modifier = modifier) {

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .width(45.dp)
                .height(45.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.height(45.dp), verticalArrangement = Arrangement.Center) {
            Text(
                "익명의 질문자",
                style = Typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = UmpaColor.Black
            )
            Text(
                "11.19", style = Typography.titleSmall,
                fontWeight = FontWeight.Normal,
                color = UmpaColor.Grey
            )
        }

    }

}

@Composable
fun QuestionContent(modifier: Modifier) {
    Text(
        "하루에 연습 몇시간 씩 하시나요?",
        modifier = modifier,
        style = Typography.bodyMedium,
        fontWeight = FontWeight.Normal,
        color = UmpaColor.Black
    )
}

@Composable
fun QuestionCommentHeader(commentCount: Int) {
    Text(
        "답변 ($commentCount)",
        style = Typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = UmpaColor.Black
    )

    Spacer(modifier = Modifier.height(20.dp))


}

@Composable
fun CommentItem(modifier: Modifier = Modifier, comment: QuestionCommentModel) {
    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (comment.isAnonymous) "익명" else comment.author,
                style = Typography.titleMedium,
                color = UmpaColor.Black
            )

            Text(
                text = comment.dateCreated,
                style = Typography.titleSmall,
                color = UmpaColor.Black
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = comment.content,
            style = Typography.bodyMedium,
            color = UmpaColor.Black
        )

        Spacer(modifier = Modifier.height(8.dp))
    }
}

private fun LazyListScope.commentList(
    questionCommentList: List<QuestionCommentModel>,
) {
    item {
        QuestionCommentHeader(questionCommentList.size)
    }

    if (questionCommentList.isEmpty()) {
        item {
            EmptyComment()
        }
    } else {
        itemsIndexed(questionCommentList) { index, comment ->
            CommentItem(comment = comment, modifier = Modifier.padding(top = 12.dp, bottom = 16.dp))
            HorizontalDivider(color = UmpaColor.LightGray, thickness = 2.dp)
        }
    }


}

@Composable
fun EmptyComment(modifier: Modifier = Modifier) {
    Text("댓글이 없습니다.", style = Typography.bodyLarge, color = UmpaColor.Black)
}

@Preview(showBackground = true)
@Composable
fun SetMajorScreenPreview() {
    UmpaTheme {
        QuestionDetailScreen()
    }
}