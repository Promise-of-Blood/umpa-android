package com.pob.umpa.ui.view.main.matching.detail.component.tab

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pob.umpa.domain.SocialLink
import com.pob.umpa.domain.TeacherInformationTabItem
import com.pob.umpa.ui.theme.Typography
import com.pob.umpa.ui.theme.UmpaColor
import com.pob.umpa.ui.view.main.matching.detail.component.card.Card

@Composable
fun TeacherInformationTab(
    data: TeacherInformationTabItem,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = spacedBy(20.dp),
        modifier = modifier.fillMaxSize(),
    ) {
        Text(
            text = data.summary, style = Typography.bodyLarge, lineHeight = 24.sp
        )
        Card {
            TeacherProfile(data)
            ExperienceList(data.experienceList)
            Text(
                text = data.introduction,
                style = Typography.bodySmall,
                lineHeight = 28.sp,
                color = UmpaColor.Black
            )
        }
    }
}

@Composable
private fun TeacherProfile(
    teacherDetail: TeacherInformationTabItem, modifier: Modifier = Modifier
) {
    Row(modifier = modifier.height(IntrinsicSize.Max)) {
        Image(
            painter = painterResource(teacherDetail.profileImage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(horizontal = 12.dp)
        ) {
            Text(
                text = "${teacherDetail.name} 선생님", style = Typography.bodyLarge
            )

            SocialLinkList(teacherDetail.socialLinkList)
        }
    }
}

@Composable
private fun SocialLinkList(socialLinkList: List<SocialLink>, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = spacedBy(8.dp),
        modifier = modifier,
    ) {
        socialLinkList.map { (icon, link) ->
            TextButton(
                onClick = { Log.d("TEST", link) },
                shape = RectangleShape,
                modifier = Modifier.size(24.dp),
                contentPadding = PaddingValues(0.dp),
            ) {
                Icon(
                    painter = painterResource(icon),
                    tint = Color.Unspecified,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@Composable
private fun ExperienceList(experienceList: List<String>, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = spacedBy(4.dp)
    ) {
        experienceList.map { experience ->
            Row(
                horizontalArrangement = spacedBy(4.dp),
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    tint = UmpaColor.Terri,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = experience, style = Typography.bodySmall, color = UmpaColor.Black
                )
            }
        }
    }
}

