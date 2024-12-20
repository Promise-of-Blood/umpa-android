package com.pob.umpa.ui.view.main.community.InformationSharing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pob.umpa.ui.view.main.community.AcceptReview.AcceptReviewScreen
import com.pob.umpa.ui.view.main.community.AcceptReview.Review
import com.pob.umpa.ui.view.main.community.AcceptReview.Spinner

@Composable
fun InformationSharingScreen() {
    var selectedMajor by remember { mutableStateOf("전공") }
    var selectedSchool by remember { mutableStateOf("학교") }

    val majors = listOf("보컬", "작곡", "연주", "뮤지컬", "음향")
    val schools = listOf("한국실용음악대학교", "서울음악예술학교", "실용음악전문대학", "예술음악고등학교")

    Box(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spinner(
                        text = selectedMajor,
                        options = majors,
                        onOptionSelected = { selectedMajor = it }
                    )

                    Spinner(
                        text = selectedSchool,
                        options = schools,
                        onOptionSelected = { selectedSchool = it }
                    )
                }
            }

            items(30) {
                Review()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InformationSharingScreenPreview() {
    AcceptReviewScreen()
}