package com.pob.umpa.ui.view.main.matching.detail

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.pob.umpa.domain.MatchingDetailModel
import com.pob.umpa.domain.MockLessonDetailData.mockAccompanimentLessonDetailData
import com.pob.umpa.domain.MockLessonDetailData.mockComposingLessonDetailData
import com.pob.umpa.domain.MockLessonDetailData.mockMrProductionLessonDetailData
import com.pob.umpa.domain.MockLessonDetailData.mockScoreProductionLessonDetailData

class MatchingDetailPreviewParameterProvider : PreviewParameterProvider<MatchingDetailModel> {
    override val values = sequenceOf(
        mockComposingLessonDetailData,
        mockScoreProductionLessonDetailData,
        mockAccompanimentLessonDetailData,
        mockMrProductionLessonDetailData,
    )
}
