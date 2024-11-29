package com.pob.umpa.ui.view.main.matching.detail.component.tab

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pob.umpa.domain.CurriculumTabItem
import com.pob.umpa.ui.theme.Typography
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.view.main.matching.detail.component.lightGrayBorder

@Composable
fun CurriculumTab(
    data: CurriculumTabItem, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .lightGrayBorder()
            .padding(vertical = 8.dp)
    ) {
        data.curriculumList.forEachIndexed { index, curriculum ->
            CurriculumItem(
                week = index + 1,
                content = curriculum,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )
            if (index != data.curriculumList.lastIndex) HorizontalDivider(
                thickness = 1.dp, color = UmpaColor.LightGray
            )
        }
    }
}

@Composable
fun CurriculumItem(
    week: Int, content: String, modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = spacedBy(12.dp),
        modifier = modifier,
    ) {
        Text(
            text = "$week 주차",
            style = Typography.bodySmall,
            color = UmpaColor.Grey,
        )
        Text(
            text = content,
            style = Typography.bodyMedium,
            color = UmpaColor.Grey,
        )
    }
}
