package com.daejol.presentation.worldcup.play

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.viewmodel.compose.viewModel
import com.daejol.presentation.worldcup.WorldCupViewModel
import com.daejol.presentation.worldcup.selection.WorldCupPreviewParameterProvider
import com.daejol.presentation.worldcup.selection.WorldCupType

@Preview
@Composable
fun WorldCupPlayScreen(
    viewModel: WorldCupViewModel = viewModel(),
) {
    Column {
        Text(text = viewModel.worldCupLevel.value)
        Text(text = viewModel.worldCupLevel.value)
    }
}