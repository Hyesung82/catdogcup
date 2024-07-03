package com.daejol.presentation.worldcup.play

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.daejol.presentation.worldcup.WorldCupViewModel

@Preview
@Composable
fun WorldCupPlayScreen(
    viewModel: WorldCupViewModel = hiltViewModel(),
) {
    Column {
        Text(text = viewModel.worldCupLevel.value)
        Text(text = viewModel.worldCupLevel.value)
    }
}