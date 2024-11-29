package com.pob.umpa.ui.view.main.matching.detail.component.tab

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material.icons.rounded.StarRate
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pob.umpa.domain.ReviewItem
import com.pob.umpa.domain.ReviewTabItem
import com.pob.umpa.domain.SuccessStoryItem
import com.pob.umpa.ui.theme.Typography
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.view.main.matching.detail.component.lightGrayBorder
import java.time.format.DateTimeFormatter
import kotlin.math.ceil
import kotlin.math.floor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReviewTab(
    data: ReviewTabItem, modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    LazyColumn(
        verticalArrangement = spacedBy(16.dp), modifier = modifier.fillMaxWidth()
    ) {
        stickyHeader {
            ReviewTabMenu(
                selectedTabIndex = selectedTabIndex,
                onTabSelected = { selectedTabIndex = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(UmpaColor.White),
            )
        }

        when (selectedTabIndex) {
            0 -> {
                item { ReviewHeader(rating = 4.5f, reviewCount = data.reviewList.size) }
                items(data.reviewList) { review ->
                    ReviewItem(review = review)
                }
            }

            else -> {
                items(data.successStoryList) { successStory ->
                    SuccessStoryItem(successStory = successStory)
                }
            }
        }
    }
}

@Composable
private fun ReviewHeader(
    rating: Float,
    reviewCount: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        StarRatingBar(rating = rating, size = 36.dp)
        Text(
            text = "$rating ($reviewCount)", style = Typography.titleMedium
        )
    }
}

@Composable
private fun ReviewItem(
    review: ReviewItem, modifier: Modifier = Modifier
) {
    val minimumLineLength = 3
    var isReadMore by remember { mutableStateOf(false) }
    var isVisibleReadMoreButton by remember { mutableStateOf(false) }
    val maxLines = if (isReadMore) 200 else minimumLineLength

    Column(
        verticalArrangement = spacedBy(6.dp),
        modifier = modifier
            .lightGrayBorder()
            .padding(12.dp),
    ) {
        Row(
            horizontalArrangement = spacedBy(12.dp),
            verticalAlignment = Alignment.Top,
            modifier = Modifier.height(IntrinsicSize.Min)
        ) {
            Image(
                painter = painterResource(review.studentProfileImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .background(UmpaColor.Main),
            )

            Column(
                verticalArrangement = spacedBy(2.dp),
                modifier = Modifier.weight(1f),
            ) {
                Row(
                    horizontalArrangement = spacedBy(4.dp),
                ) {
                    Text(
                        text = review.studentName,
                        style = Typography.bodySmall,
                        color = UmpaColor.Black
                    )
                    Text(
                        text = review.instrumentsType,
                        style = Typography.bodySmall,
                        color = UmpaColor.Grey
                    )
                }
                Text(
                    text = review.lessonPeriod, style = Typography.bodySmall, color = UmpaColor.Grey
                )
            }

            StarRatingBar(
                rating = review.stars,
                size = 20.dp,
            )
        }

        Spacer(modifier = Modifier.size(8.dp))

        Text(text = review.content,
            style = Typography.bodySmall,
            color = UmpaColor.Black,
            overflow = TextOverflow.Ellipsis,
            maxLines = maxLines,
            onTextLayout = { textLayoutResult ->
                if (textLayoutResult.lineCount > minimumLineLength - 1) {
                    if (textLayoutResult.isLineEllipsized(minimumLineLength - 1)) isVisibleReadMoreButton =
                        true
                }
            })

        if (isVisibleReadMoreButton) {
            Text(
                text = if (isReadMore) "접기" else "더보기",
                style = Typography.bodySmall,
                color = UmpaColor.Black,
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable(interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { isReadMore = !isReadMore })
            )
        }

        if (review.contentImageList.isNotEmpty()) {
            Row(
                horizontalArrangement = spacedBy(12.dp),
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxSize()
                    .horizontalScroll(rememberScrollState()),
            ) {
                review.contentImageList.forEachIndexed { index, image ->
                    Image(
                        painter = painterResource(image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(12.dp)),
                    )
                }
            }
        }

        Text(
            text = review.createdAt.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")),
            style = Typography.bodySmall,
            color = UmpaColor.Grey,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun SuccessStoryItem(
    successStory: SuccessStoryItem, modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = spacedBy(12.dp), modifier = modifier
            .lightGrayBorder()
            .padding(12.dp)
    ) {
        Text(text = "커뮤니티 / 합격 후기", style = Typography.labelSmall, color = UmpaColor.Grey)

        Row(
            horizontalArrangement = spacedBy(12.dp),
            verticalAlignment = Alignment.Top,
            modifier = Modifier.height(IntrinsicSize.Min)
        ) {
            Image(
                painter = painterResource(successStory.certificateImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp)),
            )

            Column(
                verticalArrangement = spacedBy(8.dp),
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = successStory.title,
                    style = Typography.titleMedium,
                    color = UmpaColor.Black
                )

                Text(
                    text = successStory.succeedUniversityList.joinToString(" / "),
                    style = Typography.bodySmall,
                    color = UmpaColor.Grey,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Row(
                    horizontalArrangement = spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = successStory.studentName,
                        style = Typography.bodySmall,
                        color = UmpaColor.Grey
                    )
                    Text(
                        text = successStory.instrumentsType,
                        style = Typography.bodySmall,
                        color = UmpaColor.Grey
                    )
                }
            }
        }

        Text(
            text = successStory.createdAt.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")),
            style = Typography.bodySmall,
            color = UmpaColor.Grey,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun ReviewTabMenu(
    selectedTabIndex: Int = 0, onTabSelected: (Int) -> Unit = {}, modifier: Modifier = Modifier
) {
    val tabs = listOf("리뷰", "합격 후기")

    Row(
        horizontalArrangement = spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        tabs.forEachIndexed { index, title ->
            Text(
                text = title,
                style = Typography.bodySmall,
                color = if (selectedTabIndex == index) UmpaColor.Main else UmpaColor.Black,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() }, indication = null
                ) { onTabSelected(index) },
            )
        }
    }
}

@Composable
private fun StarRatingBar(
    rating: Float,
    size: Dp = 20.dp,
    modifier: Modifier = Modifier,
) {
    val filledStars = floor(rating).toInt()
    val unfilledStars = (5 - ceil(rating)).toInt()
    val halfStar = rating.rem(1) != 0f
    val halfClipShape = GenericShape { size, _ ->
        lineTo(size.width / 2, 0f)
        lineTo(size.width / 2, size.height)
        lineTo(0f, size.height)
        close()
    }

    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(
                imageVector = Icons.Rounded.StarRate,
                contentDescription = null,
                tint = UmpaColor.Main,
                modifier = Modifier.size(size),
            )
        }
        if (halfStar) {
            Box() {
                Icon(
                    imageVector = Icons.Rounded.StarRate,
                    contentDescription = null,
                    tint = UmpaColor.LightGray,
                    modifier = Modifier.size(size),
                )
                Icon(
                    imageVector = Icons.Rounded.StarRate,
                    contentDescription = null,
                    tint = UmpaColor.Main,
                    modifier = Modifier
                        .size(size)
                        .clip(halfClipShape)
                )
            }
        }
        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Rounded.StarBorder,
                contentDescription = null,
                tint = UmpaColor.Grey,
                modifier = Modifier.size(size),
            )
        }
    }
}

