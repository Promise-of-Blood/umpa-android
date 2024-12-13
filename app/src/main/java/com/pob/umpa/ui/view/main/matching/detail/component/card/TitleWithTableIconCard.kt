package com.pob.umpa.ui.view.main.matching.detail.component.card

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pob.umpa.ui.theme.Typography
import com.pob.umpa.ui.theme.UmpaColor

@Composable
fun TitleWithTableIconCard(
    title: String,
    table: List<Pair<String, String>>,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Rounded.AttachMoney,
) {
    IconCard(
        icon = icon,
        title = title,
        alignment = Alignment.Top,
        modifier = modifier.fillMaxWidth(),
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Table(table)
    }
}

@Composable
private fun Table(data: List<Pair<String, String>>, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = spacedBy((-1).dp), modifier = modifier.fillMaxWidth()
    ) {
        data.forEach { row ->
            TableRow(row)
        }
    }
}

@Composable
private fun TableRow(data: Pair<String, String>, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = spacedBy((-1).dp), modifier = modifier.fillMaxWidth()
    ) {
        TableCell(
            text = data.first,
            modifier = Modifier.weight(1f),
        )
        TableCell(
            text = data.second,
            modifier = Modifier.weight(2f),
        )
    }
}

@Composable
private fun TableCell(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        style = Typography.bodySmall,
        color = UmpaColor.Grey,
        modifier = modifier
            .border(
                width = 1.dp,
                color = UmpaColor.LightGray,
                shape = RectangleShape,
            )
            .padding(vertical = 8.dp),
    )
}
