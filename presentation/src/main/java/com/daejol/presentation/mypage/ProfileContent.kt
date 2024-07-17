package com.daejol.presentation.mypage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.space_l))
    ) {
        val borderWidth = 2.dp

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://example.com/image.jpg")
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.sample_cat),
            contentDescription = "프로필 이미지",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .clip(CircleShape)
                .size(128.dp)
                .border(
                    BorderStroke(borderWidth, Color.White),
                    CircleShape
                )
                .padding(borderWidth)
        )
        Spacer(modifier = modifier.width(dimensionResource(id = R.dimen.space_l)))
        Column(modifier = modifier) {
            Text(text = "Profile")
            ProfileItem(key = "이름", value = "Keykat")
            ProfileItem(key = "나이", value = "5세")
            ProfileItem(key = "종", value = "Russian Blue")
            ProfileItem(key = "한줄 특징", value = "사람을 잘 문다.")
        }
    }
}

@Composable
fun ProfileItem(
    key: String,
    value: String,
) {
    Row {
        Text(
            text = key,
            modifier = Modifier.width(72.dp)
        )
        Text(text = value)
    }
}

@Preview
@Composable
fun ProfileContentPreview() {
    CatdogcupTheme {
        ProfileContent(Modifier.background(color = MaterialTheme.colorScheme.background))
    }
}
