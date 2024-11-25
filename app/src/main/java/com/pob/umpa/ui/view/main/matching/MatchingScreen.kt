package com.pob.umpa.ui.view.main.matching

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pob.umpa.R
import com.pob.umpa.domain.MatchingModel
import com.pob.umpa.domain.MockLessonData
import com.pob.umpa.ui.theme.Typography
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.theme.pretendardFontFamily
import com.pob.umpa.util.toCommaString

@Composable
fun MatchingScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {

        MatchingOptions(
            modifier = Modifier
                .background(Color.Red)
                .padding(10.dp)
        )

        MatchingList(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        )

    }
}

@Composable
fun MatchingOptions(modifier: Modifier = Modifier) {

    Row() {
        MatchingDropDownMenu(dropMenuList = lessonTypeList)

        Spacer(modifier = Modifier.padding(end = 10.dp))

        MatchingDropDownMenu(dropMenuList = InstrumentsTypeList)
    }

}


@Composable
fun MatchingDropDownMenu(modifier: Modifier = Modifier, dropMenuList: List<String>) {

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
                fontSize = 14.sp
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
fun MatchingList(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(
            MockLessonData.mockLessonData
        ) { index, item ->
            MatchingItem(matchingData = item)
            if (index < MockLessonData.mockLessonData.size - 1) {
                HorizontalDivider(thickness = 1.dp, color = UmpaColor.LightGray)
            }
        }
    }

}

@Composable
fun MatchingItem(matchingData: MatchingModel) {

    Row(
        modifier = Modifier
            .height(120.dp)
            .background(Color.White)
            .fillMaxWidth()
            .padding(0.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier
                .width(260.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceAround
        ) {

            Text(
                text = matchingData.title,
                style = Typography.titleMedium,
            )

            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = "${matchingData.teacherName} 선생님",
                    style = Typography.bodySmall,
                    color = UmpaColor.Grey
                )

                Spacer(modifier = Modifier.padding(3.dp))

                Text(
                    text = "·",
                    style = Typography.bodySmall,
                )

                Spacer(modifier = Modifier.padding(3.dp))

                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = Color.Yellow,
                    modifier = Modifier.size(16.dp)
                )

                Spacer(modifier = Modifier.padding(3.dp))

                Text(
                    text = matchingData.stars.toString(),
                    style = Typography.bodySmall,
                    color = UmpaColor.Black
                )

                Spacer(modifier = Modifier.padding(3.dp))

                Text(
                    text = "·",
                    style = Typography.bodySmall
                )

                Spacer(modifier = Modifier.padding(3.dp))

                Text(
                    text = "${matchingData.locationCity} / ${matchingData.locationArea}",
                    style = Typography.bodySmall
                )

            }
            Text(
                text = matchingData.content, maxLines = 1, overflow = TextOverflow.Ellipsis,
                style = Typography.bodySmall
            )
            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = "${matchingData.price.toCommaString()}원",
                    style = Typography.bodyLarge
                )

                Spacer(modifier = Modifier.padding(1.dp))

                Text(
                    text = "/시간",
                    style = Typography.bodySmall,
                )

            }
        }

        //ToDo image
        Image(
            painter = painterResource(id = R.drawable.find_teacher_accompanist),
            contentDescription = "Teacher Image",
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MatchingScreenPreview() {
    MatchingScreen(modifier = Modifier)
}
