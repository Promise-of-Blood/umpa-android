package com.pob.umpa.ui.view.main.matching.detail

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pob.umpa.R
import com.pob.umpa.domain.MatchingModel
import com.pob.umpa.domain.MockLessonDetailData.mockLessonDetailData
import com.pob.umpa.ui.theme.Typography
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.util.toCommaString

@Composable
fun MatchingDetailScreen(
    navController: NavController,
    lessonId: String,
    modifier: Modifier = Modifier,
) {
    val tabs = listOf("선생님 소개", "수업 소개", "커리큘럼", "리뷰")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    MatchingDetailLayout(
        topBar = {
            MatchingDetailTopBar(navController)
        },
        header = {
            MatchingDetailSummary(
                lessonId = lessonId,
                lessonSummary = mockLessonDetailData.lessonSummary,
                modifier = Modifier.padding(24.dp),
            )
        },
        stickyHeader = {
            CustomTabMenu(
                tabs = tabs,
                selectedTabIndex = selectedTabIndex,
                onTabSelected = { selectedTabIndex = it },
                modifier = Modifier
                    .background(UmpaColor.White)
                    .padding(horizontal = 24.dp),
            )
        },
        bottomBar = {
            MatchingDetailBottomToolbar(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            )
        },
        content = {
            when (selectedTabIndex) {
                0 -> TeacherInformationScreen(mockLessonDetailData.teacherDetail)
                1 -> LessonInformationScreen(mockLessonDetailData.lessonDetail)
                2 -> CurriculumScreen(mockLessonDetailData.curriculumList)
                3 -> ReviewScreen(
                    reviewList = mockLessonDetailData.reviewList,
                    successStoryList = mockLessonDetailData.successStoryList
                )
            }
        },
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MatchingDetailLayout(
    topBar: @Composable () -> Unit = {},
    header: @Composable () -> Unit = {},
    stickyHeader: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    var isHeaderHide by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val nestedScrollConnection = CustomNestedScrollConnection(isHeaderHide, scrollState)

    Scaffold(topBar = topBar, bottomBar = bottomBar) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(UmpaColor.White)
                .padding(innerPadding)
        ) {
            val density = LocalDensity.current
            var globalHeight by remember { mutableIntStateOf(0) }
            var headerHeight by remember { mutableIntStateOf(0) }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .onSizeChanged { size -> globalHeight = size.height }
                    .nestedScroll(nestedScrollConnection)
                    .verticalScroll(scrollState),
            ) {
                MatchingDetailHeaderSection(
                    onHide = { isHide -> isHeaderHide = isHide },
                ) { header() }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(with(density) { globalHeight.toDp() })
                        .background(UmpaColor.White),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .onSizeChanged { size -> headerHeight = size.height },
                    ) {
                        stickyHeader()
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(with(density) { globalHeight.toDp() - headerHeight.toDp() })
                            .padding(24.dp)
                    ) {
                        content()
                    }
                }
            }
        }
    }
}

@Composable
fun MatchingDetailHeaderSection(
    onHide: (Boolean) -> Unit,
    header: @Composable () -> Unit,
) {
    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(isVisible) { onHide(!isVisible) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .onGloballyPositioned { layoutCoordinates ->
            val bounds = layoutCoordinates.boundsInRoot()
            isVisible = bounds.top < 0 || bounds.bottom > layoutCoordinates.boundsInRoot().height
        }) {
        header()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchingDetailTopBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    TopAppBar(title = {}, colors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color.White,
        navigationIconContentColor = UmpaColor.Black,
    ), modifier = modifier, navigationIcon = {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "뒤로가기"
            )
        }
    })
}

@Composable
fun MatchingDetailBottomToolbar(modifier: Modifier = Modifier) {
    var isFavorite by remember { mutableStateOf(false) }

    fun onClickChatButton() {}

    Row(
        modifier = modifier,
        horizontalArrangement = spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        FavoriteButton(isFavorite) { isFavorite = it }
        ChatButton(
            ::onClickChatButton, Modifier.weight(1f)
        )
    }
}

@Composable
fun FavoriteButton(
    isFavorite: Boolean = false,
    onCheckedChange: (Boolean) -> Unit,
) {
    IconToggleButton(
        checked = isFavorite, onCheckedChange = onCheckedChange, modifier = Modifier.border(
            width = 1.dp,
            color = UmpaColor.LightGray,
            shape = RoundedCornerShape(8.dp),
        )
    ) {
        Icon(
            tint = if (isFavorite) UmpaColor.Main else UmpaColor.Black,
            modifier = Modifier
                .padding(4.dp)
                .size(24.dp),
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = null
        )
    }
}

@Composable
fun ChatButton(
    onClick: () -> Unit, modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(UmpaColor.Main),
        modifier = modifier.height(IntrinsicSize.Min),
    ) {
        Text(text = "채팅하기", style = Typography.bodyLarge)
    }
}

@Composable
fun MatchingDetailSummary(
    lessonId: String, lessonSummary: MatchingModel, modifier: Modifier
) {
    MatchingDetailHeaderImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
    )

    MatchingDetailHeaderText(
        lessonId = lessonId,
        lesson = lessonSummary,
        modifier = modifier,
    )
}

@Composable
fun MatchingDetailHeaderImage(
    image: Int = R.drawable.find_teacher_accompanist, modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(image),
        contentDescription = "대표 이미지",
        contentScale = ContentScale.Crop,
        modifier = modifier,
    )
}

@Composable
fun MatchingDetailHeaderText(
    lessonId: String, lesson: MatchingModel, modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = lesson.title + lessonId, style = Typography.titleMedium
        )

        Spacer(modifier = Modifier.padding(2.dp))

        Row(
            horizontalArrangement = spacedBy(4.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${lesson.teacherName} 선생님",
                style = Typography.bodySmall,
                color = UmpaColor.Grey
            )

            Text(
                text = "·",
                style = Typography.bodySmall,
            )

            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = Color.Yellow,
                modifier = Modifier.size(16.dp)
            )

            Text(
                text = lesson.stars.toString(),
                style = Typography.bodySmall,
                color = UmpaColor.Black
            )

            Text(
                text = "·", style = Typography.bodySmall
            )

            Text(
                text = "${lesson.locationCity} / ${lesson.locationArea}",
                style = Typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${lesson.price.toCommaString()}원", style = Typography.bodyLarge
            )

            Spacer(modifier = Modifier.padding(1.dp))

            Text(
                text = "/시간",
                style = Typography.bodySmall,
            )
        }
    }
}

@Composable
fun CustomTabMenu(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val tabPositions = remember {
        mutableStateListOf<Offset>().apply {
            repeat(tabs.size) { add(Offset.Zero) }
        }
    }
    val tabWidths = remember {
        mutableStateListOf<Float>().apply {
            repeat(tabs.size) { add(0f) }
        }
    }
    val density = LocalDensity.current
    val indicatorOffset by animateDpAsState(
        targetValue = with(density) {
            tabPositions.getOrNull(selectedTabIndex)?.x?.toDp() ?: 0.dp
        },
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
        label = "Indicator Offset"
    )

    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEachIndexed { index, title ->
                Box(modifier = Modifier
                    .padding(vertical = 8.dp)
                    .onGloballyPositioned { layoutCoordinates ->
                        tabPositions[index] = layoutCoordinates.positionInParent()
                        tabWidths[index] = layoutCoordinates.size.width.toFloat()
                    }
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { onTabSelected(index) }, contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        style = Typography.bodySmall,
                        color = if (selectedTabIndex == index) UmpaColor.Main else UmpaColor.Black
                    )
                }
            }
        }

        // Indicator
        Box(
            modifier = Modifier
                .offset(x = indicatorOffset)
                .width(with(density) { tabWidths[selectedTabIndex].toDp() })
                .height(3.dp)
                .background(UmpaColor.Main)
        )
    }
}
