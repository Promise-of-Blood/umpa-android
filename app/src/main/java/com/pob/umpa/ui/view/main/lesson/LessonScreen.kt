package com.pob.umpa.ui.view.main.lesson

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pob.umpa.ui.theme.pretendardFontFamily

@Composable
fun LessonScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {

        LessonOptions(
            modifier = Modifier
                .background(Color.Red)
                .padding(10.dp)
        )

        LessonList(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        )

    }
}

@Composable
fun LessonOptions(modifier: Modifier = Modifier) {

    Row() {
        LessonDropDownMenu(dropMenuList = lessonTypeList)

        Spacer(modifier = Modifier.padding(end = 10.dp))

        LessonDropDownMenu(dropMenuList = InstrumentsTypeList)
    }

}


@Composable
fun LessonDropDownMenu(modifier: Modifier = Modifier, dropMenuList: List<String>) {

    val context = LocalContext.current

    var isDropDownMenuExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(dropMenuList[0]) }

    OutlinedButton(
        onClick = { isDropDownMenuExpanded = true },
        border = BorderStroke(0.5.dp, Color.LightGray),
        shape = RoundedCornerShape(20.dp),
        contentPadding = PaddingValues(15.dp, 0.dp, 8.dp, 0.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(1.dp),
        ) {

            Text(
                text = selectedText,
                color = Color.Gray,
                fontFamily = pretendardFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )

            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Drop-down Arrow",
                tint = Color.Gray
            )


        }
    }

    DropdownMenu(
        modifier = Modifier.wrapContentSize(),
        expanded = isDropDownMenuExpanded,
        onDismissRequest = { isDropDownMenuExpanded = false }
    ) {

        dropMenuList.forEach { option ->

            DropdownMenuItem(text = { Text(text = option, fontSize = 16.sp) },
                onClick = {
                    Toast.makeText(context, option, Toast.LENGTH_SHORT).show()
                    isDropDownMenuExpanded = false
                    selectedText = option
                },
                trailingIcon = {
                    if (option == selectedText) Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null
                    )
                }
            )

        }
    }
}


@Composable
fun LessonList(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {

        Text(text = "aaa")
    }

}

@Preview(showBackground = true)
@Composable
fun LessonScreenPreview() {
    LessonScreen(modifier = Modifier)
}
