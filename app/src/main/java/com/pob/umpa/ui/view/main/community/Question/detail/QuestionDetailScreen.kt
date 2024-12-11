package com.pob.umpa.ui.view.main.community.Question.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pob.umpa.R
import com.pob.umpa.ui.theme.Typography
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.theme.UmpaTheme
import com.pob.umpa.ui.theme.pretendardFontFamily

@Composable
fun QuestionDetailScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {

        TopTitle(modifier = Modifier.fillMaxWidth())

        UserProfile(modifier = Modifier)

        Spacer(modifier = Modifier.height(30.dp))

        QuestionContent(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(100.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        QuestionComments()

//        QuestionCommentInput()
    }
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
fun QuestionComments() {
    Text(
        "답변 (5)",
        style = Typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = UmpaColor.Black
    )



}


@Preview(showBackground = true)
@Composable
fun SetMajorScreenPreview() {
    UmpaTheme {
        QuestionDetailScreen()
    }
}