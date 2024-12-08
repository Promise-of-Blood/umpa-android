package com.pob.umpa.ui.view.main

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.theme.UmpaColor.Companion.Grey
import com.pob.umpa.ui.theme.UmpaColor.Companion.Main
import com.pob.umpa.ui.theme.UmpaColor.Companion.White
import com.pob.umpa.ui.theme.pretendardFontFamily


@Composable
fun EditProfileTitle(
    modifier: Modifier,
    title: String,
    isRequired: Boolean,
    explanationText: String? = null
) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = title,
                fontFamily = pretendardFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            if (isRequired) {
                Spacer(modifier.size(8.dp))
                Text(
                    text = "필수",
                    fontFamily = pretendardFontFamily,
                    fontSize = 10.sp,
                    color = Main
                )
            }
            Spacer(modifier.size(8.dp))
            Icon(
                modifier = Modifier.size(18.dp),
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = Main
            )
        }
        if (explanationText != null) {
            Spacer(modifier = modifier.size(8.dp))
            Text(
                explanationText,
                fontFamily = pretendardFontFamily,
                fontSize = 12.sp,
                lineHeight = 20.sp,
                color = UmpaColor.Main
            )
        }


    }


}

@Composable
fun ProfilePhoto(onImageSelected: (Uri?) -> Unit) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
        onImageSelected(uri)
    }

    Column(
        modifier = Modifier
            .wrapContentSize()
    ) {
        // 프로필 사진 영역
        Box(
            modifier = Modifier
                .size(180.dp)
                .clip(shape = RoundedCornerShape(5.dp))
                .background(Grey)
                .clickable { launcher.launch("image/*") } // 갤러리 호출
        ) {
            selectedImageUri?.let { uri ->
                // 선택된 이미지 표시
                Image(
                    painter = rememberAsyncImagePainter(uri),
                    contentDescription = "Selected Profile Picture",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } ?: run {
                // 기본 이미지 또는 빈 상태 표시
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Default Profile Picture",
                    modifier = Modifier.align(Alignment.Center),
                    tint = White
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun SpinnerWithButton(optionList: List<String>, defaultText: String) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(defaultText) }

    Column(
        modifier = Modifier
            .wrapContentWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(onClick = { expanded = true }, modifier = Modifier.width(120.dp)) {
            Row {
                Text(selectedOption, color = Grey)
                Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Grey)
            }
        }
    }

    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
        optionList.forEach { option ->
            DropdownMenuItem(
                onClick = {
                    selectedOption = option
                    expanded = false
                }
            ) {
                Text(option)
            }
        }
    }
}

@Composable
fun EditText(defaultText: String, width: Dp? = null) {
    var value by remember { mutableStateOf(defaultText) }
    TextField(
        value = value,
        onValueChange = { value = it },
        textStyle = TextStyle(color = Grey),
        modifier = Modifier
            .then(
                if (width != null) {
                    Modifier.width(width)
                } else {
                    Modifier.fillMaxWidth()
                }
            )
            .height(50.dp)
            .border(width = 1.dp, color = Main, shape = RoundedCornerShape(5.dp)),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = White
        )
    )
}

@Composable
fun DefaultButton(text: String) {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(Main),
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(text = text)
    }
}


@Composable
fun CategoryTitle(modifier: Modifier, title: String) {
    Row() {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = title,
            fontFamily = pretendardFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
    }

}

@Composable
fun descriptionText(modifier: Modifier, text: String) {
    Text(
        text = text,
        fontFamily = pretendardFontFamily,
        fontSize = 10.sp,
        color = Main
    )
}


@Composable
public fun LessonPhoto(onImageSelected: (Uri?) -> Unit) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
        onImageSelected(uri)
    }

    Column(
        modifier = Modifier
            .wrapContentSize()
    ) {
        // 프로필 사진 영역
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Grey)
                .clickable { launcher.launch("image/*") } // 갤러리 호출
        ) {
            selectedImageUri?.let { uri ->
                // 선택된 이미지 표시
                Image(
                    painter = rememberAsyncImagePainter(uri),
                    contentDescription = "Selected Profile Picture",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } ?: run {
                // 기본 이미지 또는 빈 상태 표시
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = "Default Profile Picture",
                    modifier = Modifier.align(Alignment.Center),
                    tint = White
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun CheckBoxText(text: String) {
    var checked by remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically) {

        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it },
            colors = CheckboxDefaults.colors(
                checkedColor = Main,
                uncheckedColor = Grey,
                checkmarkColor = White
            ),
            modifier = Modifier.size(30.dp)
        )

        Text(text = text, fontSize = 12.sp)
    }
}

val timeList = (0..23).map { it.toString().padStart(2, '0') }




@Composable
fun BorderedLazyColumn(
    optionList: List<String>,
    selectedOption: String,
    width: Dp = 100.dp,
    onDaySelected: (String) -> Unit,
    borderWidth: Dp = 1.dp
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(width)
            .height(64.dp)
            .padding(vertical = 8.dp, horizontal = 2.dp) // 외부 여백
            .drawBehind {
                val strokeWidth = borderWidth.toPx()
                val yTop = 0f + strokeWidth / 2
                val yBottom = size.height - strokeWidth / 2

                // 위 테두리
                drawLine(
                    color = UmpaColor.LightGray,
                    start = Offset(0f, yTop),
                    end = Offset(size.width, yTop),
                    strokeWidth = strokeWidth
                )
                // 아래 테두리
                drawLine(
                    color = UmpaColor.LightGray,
                    start = Offset(0f, yBottom),
                    end = Offset(size.width, yBottom),
                    strokeWidth = strokeWidth
                )
            }
            .padding(0.dp) // 내부 여백
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(10.dp), // LazyColumn 내부 여백
        ) {
            items(optionList) { option ->
                val isSelected = option == selectedOption
                Text(
                    text = option,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        color = UmpaColor.Grey,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(UmpaColor.White)
                        .clickable { onDaySelected(option) }
                        .align(Alignment.Center)
                )
            }
        }
    }
}


@Composable
fun AddPhoto() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .border(1.dp, UmpaColor.Main, RoundedCornerShape(5.dp)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Default.PhotoCamera,
            contentDescription = null,
            tint = UmpaColor.Grey
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MultiSelectChips(options: List<String>) {

    val selectedOptions = remember { mutableStateListOf<String>() }

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        options.forEach { option ->
            Card(
                shape = RoundedCornerShape(20.dp),
                backgroundColor = if(selectedOptions.contains(option)){
                    UmpaColor.LightBlue
                } else {
                    UmpaColor.LightGray
                },
                modifier = Modifier
                    .clickable {
                        if (selectedOptions.contains(option)) {
                            selectedOptions.remove(option)
                        } else {
                            selectedOptions.add(option)
                        }
                    }
                    .padding(4.dp)
            ){
                Text(
                    text = option,
                    color = UmpaColor.Black,
                    style = TextStyle(
                        color = UmpaColor.Black,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                )
            }
        }

    }

}