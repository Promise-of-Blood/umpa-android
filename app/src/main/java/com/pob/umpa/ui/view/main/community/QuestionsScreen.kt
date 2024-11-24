package com.pob.umpa.ui.view.main.community

import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.Icons
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
                items(30) {
                    QuestionBox()
                }
            }
        }
    }
}

@Composable
fun QuestionUpload() {
    TODO("Not yet implemented")
}

@Composable
fun QuestionBox() {
    var isAnonymous by remember { mutableStateOf(false) } // 상태를 부모에서 관리
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
                QuestionInfo()
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .weight(8.5f)
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
                    AnswerField()
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
fun AnswerField() {
    var text by remember { mutableStateOf("") }
    BasicTextField(
        value = text,
        onValueChange = { newText -> text = newText },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 2.dp),
        decorationBox = { innerTextField ->
            Box(
                Modifier
                    .padding(horizontal = 4.dp, vertical = 4.dp)
                    .fillMaxWidth()
            ) {
                if (text.isEmpty()) {
                    Text(
                        text = "답변을 입력해주세요.",
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
fun QuestionInfo() {
    Column(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Text(
                text = "익명의 질문자",
                style = Typography.bodySmall
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "•",
                style = Typography.bodySmall
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "1일전",
                style = Typography.bodySmall
            )
        }
        Text(
            text = "하루에 연습 몇시간 씩 하시나요?",
            style = Typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionsScreenPreview() {
    QuestionsScreen()
}