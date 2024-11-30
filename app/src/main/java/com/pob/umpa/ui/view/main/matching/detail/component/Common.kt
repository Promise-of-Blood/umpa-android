package com.pob.umpa.ui.view.main.matching.detail.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pob.umpa.domain.AccompanimentInformationTabItem
import com.pob.umpa.domain.CurriculumTabItem
import com.pob.umpa.domain.LessonInformationTabItem
import com.pob.umpa.domain.MatchingDetailSampleCheckTabItem
import com.pob.umpa.domain.MatchingDetailTabItem
import com.pob.umpa.domain.ReviewTabItem
import com.pob.umpa.domain.ServiceInformationTabItem
import com.pob.umpa.domain.TeacherInformationTabItem
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.view.main.matching.detail.component.tab.AccompanimentInformationTab
import com.pob.umpa.ui.view.main.matching.detail.component.tab.CurriculumTab
import com.pob.umpa.ui.view.main.matching.detail.component.tab.LessonInformationTab
import com.pob.umpa.ui.view.main.matching.detail.component.tab.ReviewTab
import com.pob.umpa.ui.view.main.matching.detail.component.tab.SampleCheckTab
import com.pob.umpa.ui.view.main.matching.detail.component.tab.ServiceInformationTab
import com.pob.umpa.ui.view.main.matching.detail.component.tab.TeacherInformationTab

fun Modifier.lightGrayBorder(): Modifier = this.border(
    width = 1.dp,
    color = UmpaColor.LightGray,
    shape = RoundedCornerShape(8.dp),
)

fun Modifier.cardPadding() = this.padding(
    horizontal = 16.dp,
    vertical = 24.dp,
)

@Composable
fun MatchingDetailTabItem.Render(modifier: Modifier = Modifier) = when (this) {
    is TeacherInformationTabItem -> TeacherInformationTab(this, modifier)
    is LessonInformationTabItem -> LessonInformationTab(this, modifier)
    is ServiceInformationTabItem -> ServiceInformationTab(this, modifier)
    is AccompanimentInformationTabItem -> AccompanimentInformationTab(this, modifier)
    is CurriculumTabItem -> CurriculumTab(this, modifier)
    is ReviewTabItem -> ReviewTab(this)
    is MatchingDetailSampleCheckTabItem -> SampleCheckTab(this, modifier)
}
