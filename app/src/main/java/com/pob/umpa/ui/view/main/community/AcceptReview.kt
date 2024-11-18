package com.pob.umpa.ui.view.main.community

import android.widget.Spinner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pob.umpa.R
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.theme.pretendardFontFamily

@Composable
fun AcceptReview() {
    var selectedMajor by remember { mutableStateOf("전공") }
    var selectedSchool by remember { mutableStateOf("학교") }

    val majors = listOf("보컬", "작곡", "연주", "뮤지컬", "음향")
    val schools = listOf("한국실용음악대학교", "서울음악예술학교", "실용음악전문대학", "예술음악고등학교")

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Major Dropdown
                    Spinner(
                        text = selectedMajor,
                        options = majors,
                        onOptionSelected = { selectedMajor = it }
                    )

                    // School Dropdown
                    Spinner(
                        text = selectedSchool,
                        options = schools,
                        onOptionSelected = { selectedSchool = it }
                    )
                }
            }
            items(100) {
                Review()
            }
        }
    }
}

@Composable
fun Spinner(
    text: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
            .clickable { expanded = true }
    ) {
        Row(
            modifier = Modifier
                .background(
                    color = UmpaColor.White,
                    shape = MaterialTheme.shapes.small.copy(all = CornerSize(32.dp)) // 둥근 모양
                )
                .border(
                    width = 1.dp,
                    color = UmpaColor.MiddleGrey,
                    shape = MaterialTheme.shapes.small.copy(all = CornerSize(32.dp)) // 테두리 둥근 모양
                )
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                style = TextStyle(
                    fontFamily = pretendardFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                ),
            )

            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24), // 드롭다운 아이콘
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.size(24.dp)
            )
        }

        // DropdownMenu 표시
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(color = UmpaColor.White)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    text = {
                        Text(
                            text = option,
                            style = TextStyle(
                                fontFamily = pretendardFontFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            ),
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun Review() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        // 게시글 제목
        Text(
            text = "서울예대 최종 합격 후기",
            style = TextStyle(
                fontFamily = pretendardFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // 게시글 내용
        Text(
            text = "드디어!! 입시끝났다!!!!!! 칼바람 딱대!!!!!!!!!!!!!!!!!",
            style = TextStyle(
                fontFamily = pretendardFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // 하단 아이콘 및 정보
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "좋아요",
                    tint = UmpaColor.MiddleGrey,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "123", style = MaterialTheme.typography.bodySmall)

                Spacer(modifier = Modifier.width(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.baseline_chat_24),
                    contentDescription = "댓글",
                    colorFilter = ColorFilter.tint(UmpaColor.Grey),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "45", style = MaterialTheme.typography.bodySmall)
            }

            Text(
                text = "4 분전",
                style = MaterialTheme.typography.bodySmall,
                color = UmpaColor.Grey
            )
        }
    }
}
