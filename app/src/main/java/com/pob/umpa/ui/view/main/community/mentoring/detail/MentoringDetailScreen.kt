package com.pob.umpa.ui.view.main.community.mentoring.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pob.umpa.domain.Comment
import com.pob.umpa.domain.MentoringDetailComment
import com.pob.umpa.domain.MentoringDetailModel
import com.pob.umpa.domain.MockMentoringDetailData.mockMentoringDetailData
import com.pob.umpa.ui.theme.Typography
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.view.main.matching.detail.component.card.YoutubePlayer
import com.pob.umpa.util.parseVideoLink
import com.pob.umpa.util.toDiffString
import com.pob.umpa.util.toFormattedString

@Composable
fun MentoringDetailScreen(
    modifier: Modifier = Modifier,
) {
    val data = mockMentoringDetailData
    var value by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            item {
                MainImage(image = data.image)

                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(vertical = 12.dp),
                ) {
                    TitleText(
                        title = data.title, modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    MentorProfile(
                        data = data,
                        modifier = Modifier.padding(horizontal = 16.dp),
                    )

                    GrayDivider()

                    ContentText(
                        content = data.content,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )

                    GrayDivider()
                }
            }

            commentList(
                commentList = data.commentList,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
            )
        }

        CommentInput(
            value = value,
            setValue = { value = it },
        )
    }
}

@Composable
private fun MainImage(
    image: Int,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(image),
        contentDescription = "Main Image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp),
    )
}

@Composable
private fun TitleText(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        style = Typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = UmpaColor.Black,
        modifier = modifier,
    )
}

@Composable
private fun MentorProfile(
    data: MentoringDetailModel,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(data.mentorImage),
            contentDescription = "Profile Image",
            modifier = Modifier
                .width(45.dp)
                .height(45.dp)
                .clip(RoundedCornerShape(6.dp)),
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "${data.mentorName} 선생님",
                    style = Typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = UmpaColor.Black,
                )
                Text(
                    text = data.mentorInstrumentsType,
                    style = Typography.labelSmall,
                    fontWeight = FontWeight.Normal,
                    color = UmpaColor.Grey,
                )
            }
            Text(
                text = data.createdAt.toFormattedString(),
                style = Typography.labelSmall,
                fontWeight = FontWeight.Normal,
                color = UmpaColor.Grey,
            )
        }
    }
}

@Composable
private fun ContentText(
    content: String,
    modifier: Modifier = Modifier,
) {
    content.split("\n").forEach { text ->
        if (text.startsWith("https://www.youtube.com/")) {
            val videoId = text.parseVideoLink()
            YoutubePlayer(
                videoId = videoId, modifier = modifier
            )
        } else {
            Text(
                text = text,
                style = Typography.bodySmall,
                fontWeight = FontWeight.Normal,
                color = UmpaColor.Black,
                lineHeight = 20.sp,
                modifier = modifier,
            )
        }
    }
}

private fun LazyListScope.commentList(
    commentList: List<MentoringDetailComment>,
    modifier: Modifier = Modifier,
) {
    item {
        Text(
            text = "댓글 (${commentList.size})",
            style = Typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = UmpaColor.Black,
            modifier = modifier,
        )

        Spacer(modifier = Modifier.height(12.dp))
    }
    if (commentList.isEmpty()) {
        item {
            EmptyComment()
        }
    } else {
        itemsIndexed(commentList) { index, comment ->
            CommentItem(
                comment = comment,
                modifier = modifier,
            )
            if (index != commentList.lastIndex) GrayDivider(modifier = modifier)
        }
    }
}

@Composable
private fun CommentItem(
    comment: MentoringDetailComment,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = comment.username,
                style = Typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = UmpaColor.Black,
            )
            Text(
                text = comment.createdAt.toDiffString(),
                style = Typography.labelSmall,
                fontWeight = FontWeight.Normal,
                color = UmpaColor.Black,
            )
        }
        Text(
            text = comment.content,
            style = Typography.bodySmall,
            fontWeight = FontWeight.Normal,
            color = UmpaColor.Black,
        )
    }
}

@Composable
private fun EmptyComment(
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 52.dp),
    ) {
        Text(
            text = "댓글이 없습니다\n첫번째 댓글을 달아주세요",
            style = Typography.labelSmall,
            fontWeight = FontWeight.Normal,
            color = UmpaColor.Grey,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun CommentInput(
    value: String = "",
    setValue: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.imePadding()) {
        GrayDivider()
        BasicTextField(
            value = value,
            onValueChange = setValue,
            singleLine = false,
            maxLines = 3,
            textStyle = Typography.bodySmall.copy(
                fontWeight = FontWeight.Normal,
                color = UmpaColor.Black,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            decorationBox = @Composable { innerTextField ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 12.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(UmpaColor.LightGray)
                        .padding(horizontal = 12.dp, vertical = 16.dp)
                ) {
                    if (value.isEmpty()) Text(
                        text = "댓글을 입력해주세요",
                        style = Typography.bodySmall,
                        fontWeight = FontWeight.Normal,
                        color = UmpaColor.Grey,
                    )
                    innerTextField()
                }
            },
        )
    }
}

@Composable
private fun GrayDivider(
    modifier: Modifier = Modifier,
) {
    HorizontalDivider(
        color = UmpaColor.LightGray, thickness = (1.5).dp, modifier = modifier,
    )
}
