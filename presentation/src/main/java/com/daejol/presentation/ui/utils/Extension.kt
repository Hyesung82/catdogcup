package com.daejol.presentation.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun Dp.sp() = with(LocalDensity.current) {
    this@sp.toSp()
}

@Composable
fun Dp.px() = with(LocalDensity.current) { this@px.toPx() }
