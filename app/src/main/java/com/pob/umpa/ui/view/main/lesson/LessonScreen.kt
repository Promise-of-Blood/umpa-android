package com.pob.umpa.ui.view.main.lesson

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LessonScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {

        LessonOption(modifier = Modifier
            .background(Color.LightGray)
            .padding(10.dp))

        LessonList(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)
        )

    }
}

@Composable
fun LessonOption(modifier: Modifier = Modifier) {

    Row(modifier = modifier) {
        Text(text = "fpadsf")
    }

}

@Composable
fun LessonList(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {

    }
    Text(text = "aaa")

}

@Preview(showBackground = true)
@Composable
fun LessonScreenPreview() {
    LessonScreen(modifier = Modifier)
}
