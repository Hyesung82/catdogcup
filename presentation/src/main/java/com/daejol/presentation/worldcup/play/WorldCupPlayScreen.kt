package com.daejol.presentation.worldcup.play

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.daejol.presentation.worldcup.WorldCupViewModel

@Composable
fun WorldCupPlayScreen(
    viewModel: WorldCupViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = viewModel.worldCupLevel.value)

        val first = if (viewModel.currentMatchImages.value.size > 1) {
            viewModel.currentMatchImages.value.first()
        } else {
            null
        }

        Text(text = first?.id.toString())
        Surface(
            onClick = {
                viewModel.updateMatch(
                    winner = 0,
                    onMatchEnd = {

                    }
                )
            }
        ) {
            AsyncImage(
                model = first?.url,
                contentDescription = "",
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )
        }

        val secondUrl = if (viewModel.currentMatchImages.value.size > 1) {
            viewModel.currentMatchImages.value[1]
        } else {
            null
        }

        Surface(
            onClick = {
                viewModel.updateMatch(
                    winner = 0,
                    onMatchEnd = {

                    }
                )
            }
        ) {
            AsyncImage(
                model = secondUrl?.url,
                contentDescription = "",
            )
        }
        Text(text = secondUrl?.id.toString())

//        Text(text = )
    }
}