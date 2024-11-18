package com.pob.umpa.ui.view.main.matching.detail

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pob.umpa.R
import com.pob.umpa.domain.MatchingModel
import com.pob.umpa.domain.MockLessonData.mockLessonData
import com.pob.umpa.domain.MockTeacherData.mockTeacherData
import com.pob.umpa.ui.theme.Typography
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.util.toCommaString

@Composable
fun MatchingDetailScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = spacedBy(24.dp),
            modifier = Modifier
                .verticalScroll(scrollState)
                .weight(1f)
        ) {
            MatchingDetailHeaderImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )

            MatchingDetailHeaderText(
                lesson = mockLessonData.random(), modifier = Modifier.padding(
                    horizontal = 24.dp
                )
            )
            MatchingDetailTab(
                modifier = Modifier.padding(
                    horizontal = 24.dp
                )
            )
        }

        BottomToolbar(
            modifier = Modifier.padding(
                horizontal = 12.dp, vertical = 8.dp
            )
        )
    }
}

@Composable
fun BottomToolbar(modifier: Modifier = Modifier) {
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
fun MatchingDetailHeaderImage(modifier: Modifier = Modifier) {
    Image(painter = painterResource(id = R.drawable.find_teacher_accompanist),
        contentDescription = "Teacher Image",
        contentScale = ContentScale.Crop,
        modifier = modifier.drawWithCache {
            val gradient = Brush.verticalGradient(
                colors = listOf(Color.Transparent, UmpaColor.LightBlue),
                startY = size.height / 3,
                endY = size.height,
            )
            onDrawWithContent {
                drawContent()
                drawRect(gradient, blendMode = BlendMode.Multiply)
            }
        })
}

@Composable
fun MatchingDetailHeaderText(
    lesson: MatchingModel, modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = lesson.title, style = Typography.titleMedium
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
fun MatchingDetailTab(modifier: Modifier = Modifier) {
    val tabs = listOf("선생님 소개", "수업 소개", "커리큘럼", "리뷰")

    CustomTabMenu(tabs = tabs, modifier = modifier) { selectedTabIndex ->
        Box(
            modifier = Modifier.padding(
                vertical = 24.dp,
            )
        ) {
            when (selectedTabIndex) {
                0 -> TeacherInformationScreen(mockTeacherData)
                1 -> LessonInformationScreen(
                    modifier = Modifier
                        .background(UmpaColor.Grey)
                        .fillMaxWidth()
                        .height(500.dp)
                )

                2 -> CurriculumScreen(
                    modifier = Modifier
                        .background(UmpaColor.LightGray)
                        .fillMaxWidth()
                        .height(500.dp)
                )

                3 -> ReviewScreen(
                    modifier = Modifier
                        .background(UmpaColor.LightBlue)
                        .fillMaxWidth()
                        .height(500.dp)
                )
            }
        }
    }
}

@Composable
fun CustomTabMenu(
    tabs: List<String>, modifier: Modifier = Modifier, content: @Composable (Int) -> Unit
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
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
                    ) { selectedTabIndex = index }, contentAlignment = Alignment.Center
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

        content(selectedTabIndex)
    }
}
