package com.pob.umpa.ui.view.main.matching.detail.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.theme.pretendardFontFamily

@Composable
fun WatermarkImageListCard(imageList: List<Int>, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        imageList.forEachIndexed { index, image ->
            Watermark {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
        }
    }
}

@Composable
private fun Watermark(
    content: @Composable BoxScope.() -> Unit,
) {
    var imageHeight by remember { mutableIntStateOf(0) }

    Box(modifier = Modifier
        .fillMaxWidth()
        .onGloballyPositioned { layoutCoordinates ->
            imageHeight = layoutCoordinates.size.height
        }
        .clip(RectangleShape)) {
        content()

        if (imageHeight > 0) {
            Layout(content = {
                repeat(9) {
                    Text(
                        text = "THIS IS\nSAMPLE",
                        fontFamily = pretendardFontFamily,
                        fontSize = 40.sp,
                        lineHeight = 50.sp,
                        fontWeight = FontWeight.Normal,
                        color = UmpaColor.Main.copy(alpha = 0.4f),
                        letterSpacing = 8.sp,
                    )
                }
            }) { measurableList, constraints ->
                val placeableList: List<Placeable> =
                    measurableList.map { measurable -> measurable.measure(constraints) }

                layout(constraints.maxWidth, imageHeight) {
                    placeableList.forEachIndexed { index, item ->
                        val indexedTileSize: Int = index * 300 + 40
                        item.placeRelativeWithLayer(
                            40, indexedTileSize
                        )
                    }
                }
            }
        }
    }
}
