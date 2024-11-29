package com.pob.umpa.ui.view.main.matching.detail.component.card

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pob.umpa.ui.theme.Typography
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.view.main.matching.detail.component.cardPadding
import com.pob.umpa.ui.view.main.matching.detail.component.lightGrayBorder

@Composable
fun Card(
    title: String? = null,
    description: String? = null,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {
    Column(
        verticalArrangement = spacedBy(24.dp),
        modifier = modifier
            .fillMaxWidth()
            .lightGrayBorder()
            .cardPadding(),
    ) {
        if (title != null) {
            Text(text = title, style = Typography.titleMedium)
        }
        if (description != null) {
            Text(
                text = description,
                style = Typography.bodySmall,
                color = UmpaColor.Black,
                lineHeight = 24.sp
            )
        }
        content()
    }
}

@Composable
fun IconCard(
    icon: ImageVector,
    title: String? = null,
    titleList: List<String>? = null,
    description: String? = null,
    label: String? = null,
    alignment: Alignment.Vertical = Alignment.CenterVertically,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {
    var textLines by remember { mutableIntStateOf(0) }

    Row(
        verticalAlignment = if (textLines >= 3) Alignment.Top else alignment,
        horizontalArrangement = spacedBy(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .lightGrayBorder()
            .cardPadding()
            .height(IntrinsicSize.Max),
    ) {
        Icon(
            imageVector = icon,
            tint = UmpaColor.Black,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )

        Column(
            verticalArrangement = spacedBy(4.dp),
            modifier = Modifier.weight(1f),
        ) {
            if (title != null) {
                TitleText(title)
            }
            titleList?.forEach { title ->
                TitleText(title)
            }
            if (description != null) {
                Text(text = description,
                    style = Typography.bodySmall,
                    color = UmpaColor.Grey,
                    onTextLayout = { textLayoutResult ->
                        textLines = textLayoutResult.lineCount
                    })
            }
            if (label != null) {
                Text(
                    text = label,
                    style = Typography.labelSmall,
                    color = UmpaColor.Main,
                )
            }
            content()
        }
    }
}

@Composable
private fun TitleText(title: String) {
    Text(
        text = title,
        style = Typography.titleSmall,
        color = UmpaColor.Black,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}
