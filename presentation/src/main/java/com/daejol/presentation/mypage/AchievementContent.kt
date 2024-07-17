package com.daejol.presentation.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.daejol.presentation.R
import com.daejol.presentation.ui.theme.CatdogcupTheme
import com.daejol.presentation.ui.theme.Typography

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AchievementContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = dimensionResource(id = R.dimen.space_l))
    ) {
        Text(
            text = "활동 업적",
            style = Typography.titleLarge
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_m)))
        FlowRow(
            maxItemsInEachRow = 3
        ) {
            repeat(14) {
                AchievementItem()
            }
        }
    }
}

@Composable
private fun AchievementItem(
    imageUrl: String = ""
) {
    Column {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.sample_cat),
            contentDescription = "활동 업적",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(116.dp)
        )
        Text(text = "선택의 시작")
    }
}

@Preview
@Composable
fun AchievementContentPreview() {
    CatdogcupTheme {
        AchievementContent(
            Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
        )
    }
}
