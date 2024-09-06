package com.daejol.presentation.ui.worldcup.play

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.daejol.presentation.model.Graph
import com.daejol.presentation.ui.theme.BorderRadius
import com.daejol.presentation.ui.theme.CustomRichText
import com.daejol.presentation.ui.theme.CustomTextStyle
import com.daejol.presentation.ui.theme.MoveSans
import com.daejol.presentation.ui.theme.Orange100
import com.daejol.presentation.ui.theme.Padding
import com.daejol.presentation.ui.theme.Pretendard
import com.daejol.presentation.ui.theme.RichTextAlign
import com.daejol.presentation.ui.theme.RichTextDecoration
import com.daejol.presentation.ui.theme.White100
import com.daejol.presentation.ui.worldcup.WorldCupViewModel

@Composable
fun WorldCupPlayScreen(
    viewModel: WorldCupViewModel,
    navController: NavController? = null
) {
    val imageWidth = LocalConfiguration.current.screenWidthDp.dp / 5 * 4 - 10.dp
    val imageHeight = LocalConfiguration.current.screenWidthDp.dp / 5 * 4 - 20.dp

    val descTextStyle1 = CustomTextStyle(
        fontSize = 24f,
        fontWeight = FontWeight.Bold,
        fontColor = White100,
        fontFamily = MoveSans
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        CustomRichText(
            defaultFontFamily = Pretendard,
            defaultTextSize = 24f,
            textAlign = RichTextAlign.Center
        ) {
            RichText(text = viewModel.worldCupLevel.value)
        }
        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            val firstUrl = if (viewModel.currentMatchImages.value.size > 1) {
                println("[keykat] viewModel.currentMatchImages: ${viewModel.currentMatchImages}")
                viewModel.currentMatchImages.value[0]
            } else {
                null
            }

            val secondUrl = if (viewModel.currentMatchImages.value.size > 1) {
                viewModel.currentMatchImages.value[1]
            } else {
                null
            }

            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomRichText(
                    defaultFontFamily = MoveSans,
                    textAlign = RichTextAlign.Center
                ) {
                    RichText(text = firstUrl?.imageEntity?.breeds?.first()?.name ?: "")
                }
                Spacer(modifier = Modifier.height(10.dp))

                Surface(
                    onClick = {
                        viewModel.updateMatch(
                            winner = 0,
                            onMatchEnd = {
                                onMatchEnd(navController)
                            }
                        )
                    },
                ) {
                    AsyncImage(
                        model = firstUrl?.imageRequest,
                        contentDescription = "",
                        modifier = Modifier
                            .width(imageWidth)
                            .height(imageHeight)
                            .padding(5.dp)
                            .shadow(
                                elevation = 1.5.dp,
                                shape = RoundedCornerShape(
                                    topStart = 20.dp,
                                    topEnd = 20.dp
                                )
                            ),
                        contentScale = ContentScale.Crop
                    )
                }
                Surface(
                    onClick = {
                        viewModel.updateMatch(
                            winner = 1,
                            onMatchEnd = {
                                onMatchEnd(navController)
                            }
                        )
                    }
                ) {
                    AsyncImage(
                        model = secondUrl?.imageRequest,
                        contentDescription = "",
                        modifier = Modifier
                            .width(imageWidth)
                            .height(imageHeight)
                            .padding(5.dp)
                            .shadow(
                                elevation = 1.5.dp,
                                shape = RoundedCornerShape(
                                    bottomStart = 20.dp,
                                    bottomEnd = 20.dp
                                )
                            ),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
                CustomRichText(
                    defaultFontFamily = MoveSans,
                    textAlign = RichTextAlign.Center
                ) {
                    RichText(text = secondUrl?.imageEntity?.breeds?.first()?.name ?: "")
                }
            }
            CustomRichText(
                defaultFontFamily = MoveSans,
                textAlign = RichTextAlign.Center
            ) {
                RichText(
                    "VS",
                    textStyle = descTextStyle1,
                    decoration = RichTextDecoration(
                        background = Orange100,
                        borderRadius = BorderRadius(100f, 100f, 100f, 100f),
                        padding = Padding(6f, 2f, 6f, 2f)
                    ),
                )
            }
        }
    }
}

private fun onMatchEnd(navController: NavController?) {
    navController?.navigate(route = Graph.WorldCupResult.route) {
        popUpTo(0)
    }
}
