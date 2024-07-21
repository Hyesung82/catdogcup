package com.daejol.presentation.worldcup.result

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.daejol.presentation.ui.theme.CustomRichText
import com.daejol.presentation.ui.theme.Gimpo
import com.daejol.presentation.ui.theme.Grey10
import com.daejol.presentation.ui.theme.MoveSans
import com.daejol.presentation.ui.theme.Orange100
import com.daejol.presentation.ui.theme.RichTextAlign
import com.daejol.presentation.ui.theme.White100
import com.daejol.presentation.worldcup.WorldCupViewModel

@Composable
fun WorldCupResultScreen(
    viewModel: WorldCupViewModel,
    navController: NavController? = null
) {
    val imageWidth = LocalConfiguration.current.screenWidthDp.dp / 5 * 4 - 10.dp
    val imageHeight = LocalConfiguration.current.screenWidthDp.dp / 5 * 4 - 20.dp

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        CustomRichText(
            textAlign = RichTextAlign.Center,
            defaultFontFamily = Gimpo,
            defaultTextSize = 24f
        ) {
            RichText(text = "나의 최애는?")
        }
        Spacer(modifier = Modifier.height(24.dp))
        AsyncImage(
            model = viewModel.getWinnerImage(),
            contentDescription = "",
            modifier = Modifier
                .width(imageWidth)
                .height(imageHeight)
                .padding(5.dp)
                .shadow(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomStart = 20.dp,
                        bottomEnd = 20.dp
                    )
                ),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomRichText(
            textAlign = RichTextAlign.Center,
            defaultFontFamily = MoveSans,
            defaultFontWeight = FontWeight.Bold,
            defaultTextSize = 20f
        ) {
            RichText(text = viewModel.getWinnerName() ?: "")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // TODO: RichText로 쓰면 줄 바꿈이 안됨
//        CustomRichText(
//            textAlign = RichTextAlign.Center,
//            defaultFontFamily = MoveSans,
//            defaultFontWeight = FontWeight.Bold,
//            defaultTextSize = 12f,
//        ) {
//            RichText(text = viewModel.getWinnerDescription() ?: "")
//        }
        Text(
            text = viewModel.getWinnerDescription() ?: "",
            fontFamily = MoveSans,
            fontSize = 12.sp,
            modifier = Modifier
                .width(imageWidth),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ShareButton()

            Spacer(modifier = Modifier.width(24.dp))

            BookMarkButton()

            Spacer(modifier = Modifier.width(24.dp))

            DownloadButton()
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 다른 월드컵 하러 가기
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .width(imageWidth),
            shape = RoundedCornerShape(
                corner = CornerSize(50.dp)
            )

        ) {
            Text(
                text = "다른 월드컵 하러 가기",
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun ShareButton() {
    // share
    IconButton(
        onClick = { },
        modifier = Modifier
            .background(Orange100, CircleShape)
            .width(36.dp)
            .height(36.dp)
    ) {
        Icon(
            imageVector = Icons.Rounded.Share,
            contentDescription = "",
            tint = White100,
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)
//                .background(White100)
        )
    }
}

@Composable
fun BookMarkButton() {
    // share
    IconButton(
        onClick = { },
        modifier = Modifier
            .background(Orange100, CircleShape)
            .width(36.dp)
            .height(36.dp)
    ) {
        Icon(
            imageVector = Icons.Rounded.Bookmark,
            contentDescription = "",
            tint = White100,
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)
//                .background(White100)
        )
    }
}

@Composable
fun DownloadButton() {
    // share
    IconButton(
        onClick = { },
        modifier = Modifier
            .background(Orange100, CircleShape)
            .width(36.dp)
            .height(36.dp)
    ) {
        Icon(
            imageVector = Icons.Rounded.Download,
            contentDescription = "",
            tint = White100,
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)
//                .background(White100)
        )
    }
}