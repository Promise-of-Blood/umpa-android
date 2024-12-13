package com.pob.umpa.ui.view.main


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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pob.umpa.R
import com.pob.umpa.domain.MatchingModel
import com.pob.umpa.domain.MockLessonData
import com.pob.umpa.ui.theme.Typography
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.theme.UmpaColor.Companion.White
import com.pob.umpa.ui.theme.pretendardFontFamily
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManagementServiceScreen(modifier: Modifier = Modifier, navController: NavController) {

    val showBottomSheet = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 60.dp, horizontal = 32.dp)
        ) {
            DefaultButton(text = "레슨/서비스 추가 하기", onClick = { showBottomSheet.value = true })
        }
    }

    if (showBottomSheet.value) {
        ServiceSelectionBottomSheet(
            onDismiss = { showBottomSheet.value = false },
            onSelect = { selectedService ->
                showBottomSheet.value = false
                when (selectedService) {
                    "레슨" -> navController.navigate("lessonProfileScreen")
                    "반주" -> navController.navigate("accompanistProfileScreen")
                    "악보 제작" -> navController.navigate("scoreMakingProfileScreen")
                    "Mr 제작" -> navController.navigate("mrMakingProfileScreen")
                }
            }
        )
    }
}


@Composable
fun ServiceInfoCard(serviceData: MatchingModel) {

    Box(
        modifier = Modifier.border(
            width = 1.dp,
            shape = RoundedCornerShape(5.dp),
            color = UmpaColor.Main
        )
    ) {

        Column(
            Modifier
                .wrapContentSize()
                .padding(bottom = 16.dp, start = 8.dp, end = 4.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "${serviceData.matchingType} / ${serviceData.instrumentsType}",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = pretendardFontFamily,
                        color = UmpaColor.Grey
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "공개 여부",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = pretendardFontFamily,
                            color = UmpaColor.Grey
                        )
                    )
                    Spacer(modifier = Modifier.size(2.dp))
                    MenuSwitch(modifier = Modifier)
                }
            }

            Spacer(modifier = Modifier.size(8.dp))


            Row(
                modifier = Modifier
                    .height(90.dp),
                verticalAlignment = Alignment.CenterVertically // Row 내부 항목을 세로 중앙 정렬
            ) {
                // 이미지
                Image(
                    painter = painterResource(id = R.drawable.find_teacher_accompanist),
                    contentDescription = "Teacher Image",
                    modifier = Modifier
                        .size(90.dp), // 이미지 높이를 Row 높이에 맞춤
                )

                Spacer(modifier = Modifier.width(8.dp))

                // 텍스트 정보
                Column(
                    modifier = Modifier
                        .fillMaxHeight(), // Column이 Row 높이를 모두 차지
                    verticalArrangement = Arrangement.SpaceBetween // 요소 간 간격 균등 배치
                ) {
                    // 제목
                    Text(
                        text = serviceData.title,
                        style = TextStyle(
                            fontFamily = pretendardFontFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis // 텍스트가 넘칠 경우 줄임표 처리
                    )

                    // 평점 및 위치
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = Color.Yellow,
                            modifier = Modifier.size(16.dp) // 아이콘 크기 제한
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = serviceData.stars.toString(),
                            style = TextStyle(
                                fontFamily = pretendardFontFamily,
                                fontWeight = FontWeight.Bold
                            )
                        )

                        Text(
                            text = " · ${serviceData.locationCity}/${serviceData.locationArea}",
                            style = Typography.bodySmall
                        )
                    }

                    Text(
                        text = serviceData.content,
                        style = Typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${serviceData.price}원",
                            style = TextStyle(
                                fontFamily = pretendardFontFamily,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = "/시간",
                            style = Typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceSelectionBottomSheet(
    onDismiss: () -> Unit,
    onSelect: (String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true) // 바텀시트 상태 관리
    val coroutineScope = rememberCoroutineScope()
    val services = listOf("레슨", "반주", "악보 제작", "MR 제작")

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = UmpaColor.White
    ) {
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            services.forEachIndexed() { index, service ->
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clickable {
                            coroutineScope.launch {
                                sheetState.hide() // 바텀시트 숨기기
                                onSelect(service) // 선택한 서비스 전달
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = service,
                        modifier = Modifier
                            .wrapContentSize(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                if (index != services.lastIndex) {
                    HorizontalDivider(Modifier.padding(vertical = 0.dp), thickness = 0.5.dp)
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ManagementServicePreview() {
    ManagementServiceScreen(navController = NavController(LocalContext.current))
}
