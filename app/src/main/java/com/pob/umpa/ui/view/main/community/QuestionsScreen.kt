package com.pob.umpa.ui.view.main.community

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pob.umpa.R
import com.pob.umpa.domain.MockCommentsData
import com.pob.umpa.domain.QuestionCommentModel
import com.pob.umpa.ui.theme.Typography
import com.pob.umpa.ui.theme.UmpaColor

@Composable
fun QuestionsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                item { QuestionUpload() }
                item { Divider(modifier = Modifier.height(1.dp)) }
                items(30) {
                    QuestionBox(MockCommentsData.mockCommentsData)
                }
            }
        }
    }
}

@Composable
fun QuestionUpload() {
    var isAnonymous by remember { mutableStateOf(false) } // 상태를 부모에서 관리
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = UmpaColor.White,
            )
            .padding(vertical = 8.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfileImage()
                Spacer(modifier = Modifier.width(8.dp))
                QuestionUploadInfo("오리너구리9", "질문을 입력해주세요")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .height(35.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = UmpaColor.Main),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "등록",
                        style = Typography.bodySmall,
                        color = UmpaColor.White
                    )
                }
            }

        }
    }

}

@Composable
fun QuestionUploadInfo(user: String, question: String) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .height((50.dp))
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(
                    text = user,
                    style = Typography.bodySmall,
                    color = UmpaColor.Black
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "학생",
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(UmpaColor.Terri)
                        .padding(horizontal = 4.dp, vertical = 2.dp), // 내부 패딩 추가
                    style = Typography.bodySmall,
                    color = UmpaColor.White
                )
            }
            Row {
                Text(
                    text = "익명",
                    style = Typography.bodySmall
                )
                Spacer(modifier = Modifier.width(4.dp))
                CustomCheckbox(checked = false, onCheckedChange = {})
            }
        }
        AnswerField(question)
    }
}


@Composable
fun QuestionBox(comments: List<QuestionCommentModel>?) {
    var isAnonymous by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = UmpaColor.LightBlue,
                shape = RoundedCornerShape(16.dp),
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column {
            Row {
                ProfileImage()
                Spacer(modifier = Modifier.width(8.dp))
                QuestionInfo("익명의 질문자", 1, "하루에 연습 몇시간씩 하시나요?")
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_mode_comment_24),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "0", style = Typography.bodySmall)
            }
            Comments(comments = comments)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .weight(8.5f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = UmpaColor.LightGray)
                        .padding(vertical = 4.dp, horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AnonymousCheckbox(
                        isChecked = isAnonymous,
                        onCheckedChange = { isAnonymous = it }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    AnswerField("답변을 입력해주세요.")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .weight(1.8f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = UmpaColor.Main),
                    contentAlignment = Alignment.Center // 아이콘 중앙 정렬
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = UmpaColor.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

        }
    }
}

@Composable
fun Comments(comments: List<QuestionCommentModel>?) {
    val isSingleComment = comments?.size == 1
    var isExpanded by remember { mutableStateOf(false) }

    if (comments.isNullOrEmpty()) {
        return // 댓글이 없으면 아무것도 표시하지 않음
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        if (isExpanded) {
            // 전체 댓글 표시
            comments.forEachIndexed { index, comment ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = UmpaColor.Main,
                            shape = RoundedCornerShape(
                                topStart = if (index == 0) 12.dp else 0.dp,
                                topEnd = if (index == 0) 12.dp else 0.dp,
                                bottomStart = if (index == comments.lastIndex) 12.dp else 0.dp,
                                bottomEnd = if (index == comments.lastIndex) 12.dp else 0.dp
                            )
                        )
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RowComment(
                        index = index,
                        comment = comment,
                    )
                    // 댓글 더 보기 버튼
                    if (!isSingleComment) {
                        IconButton(
                            onClick = { isExpanded = !isExpanded },
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Expand Comments",
                                tint = Color.Gray
                            )
                        }
                    }
                }
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = UmpaColor.Main,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RowComment(
                    index = 0,
                    comment = comments[0],
                )
                if (!isSingleComment) {
                    IconButton(
                        onClick = { isExpanded = !isExpanded },
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Expand Comments",
                            tint = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RowComment(index: Int, comment: QuestionCommentModel) {
    Row {
        // 닉네임
        Text(
            text = if (comment.isAnonymous) "익명" else comment.author,
            style = Typography.bodySmall,
            modifier = Modifier.weight(1f)
        )

        // 댓글 내용
        Text(
            text = comment.content,
            style = Typography.bodyMedium,
            modifier = Modifier.weight(2f)
        )

        // 작성일자
        Text(
            text = comment.dateCreated,
            style = Typography.bodySmall,
            color = Color.Gray
        )
    }
}

@Composable
fun AnonymousCheckbox(isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomCheckbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange, // 부모에서 전달받은 상태와 변경 함수 전달
            size = 20.dp // 크기 조정
        )
        Spacer(modifier = Modifier.width(4.dp)) // 텍스트와 간격 조정
        Text(
            text = "익명",
            style = Typography.bodySmall
        )
    }
}

@Composable
fun CustomCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    size: Dp = 16.dp,
    color: Color = UmpaColor.Main
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(
                color = if (checked) color else UmpaColor.Transparent,
                shape = RoundedCornerShape(4.dp) // 모서리 둥글게
            )
            .border(
                width = 1.dp,
                color = if (checked) UmpaColor.Transparent else UmpaColor.MiddleGrey,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { onCheckedChange(!checked) },
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(
                imageVector = Icons.Default.Check, // 체크 아이콘 사용
                contentDescription = null,
                tint = UmpaColor.White,
                modifier = Modifier.size(size * 0.6f) // 아이콘 크기 조정
            )
        }
    }
}


@Composable
fun AnswerField(hintText: String) {
    var text by remember { mutableStateOf("") }
    BasicTextField(
        value = text,
        onValueChange = { newText -> text = newText },
        modifier = Modifier
            .fillMaxWidth(),
        decorationBox = { innerTextField ->
            Box(
                Modifier
                    .fillMaxWidth()
            ) {
                if (text.isEmpty()) {
                    Text(
                        text = hintText,
                        style = Typography.bodySmall,
                        color = UmpaColor.Grey
                    )
                }
                innerTextField() // 실제 텍스트 필드
            }
        },
        textStyle = Typography.bodySmall
    )
}

@Composable
fun ProfileImage() {
    Image(
        modifier = Modifier
            .size(50.dp)
            .background(
                UmpaColor.MiddleGrey,
                shape = androidx.compose.foundation.shape.CircleShape
            )
            .padding(4.dp),
        painter = painterResource(id = R.drawable.find_teacher_accompanist),
        contentDescription = "profile image",
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun QuestionInfo(user: String, uploadDate: Int, question: String) {
    Column(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Text(
                text = user,
                style = Typography.bodySmall
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "•",
                style = Typography.bodySmall
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "$uploadDate 일전",
                style = Typography.bodySmall
            )
        }
        Text(
            text = question,
            style = Typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionsScreenPreview() {
    QuestionsScreen()
}