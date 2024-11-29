package com.pob.umpa.ui.view.main.matching.detail.component.tab

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.Computer
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pob.umpa.domain.ServiceInformationTabItem
import com.pob.umpa.ui.view.main.matching.detail.component.card.Card
import com.pob.umpa.ui.view.main.matching.detail.component.card.IconCard
import com.pob.umpa.ui.view.main.matching.detail.component.card.TitleWithTableIconCard
import com.pob.umpa.util.toCommaString

@Composable
fun ServiceInformationTab(
    data: ServiceInformationTabItem,
    modifier: Modifier = Modifier,
) = with(data) {
    Column(
        verticalArrangement = spacedBy(24.dp),
        modifier = modifier,
    ) {
        TitleWithTableIconCard(
            title = "가격 안내", table = price.toPriceTable()
        )
        IconCard(
            icon = Icons.Rounded.AccessTime, title = "평균 소요 시간", description = averageTimeCost
        )
        IconCard(
            icon = Icons.Rounded.Edit, title = "수업 횟수", description = lessonCount
        )
        IconCard(
            icon = Icons.Rounded.Computer, title = "사용 프로그램", description = usageProgram
        )
        Card(
            title = "서비스 소개", description = serviceInformation
        )
    }
}

private fun List<Pair<String, Int>>.toPriceTable() =
    listOf("전공" to "가격") + this.map { it.first to "${it.second.toCommaString()} / 장" }
