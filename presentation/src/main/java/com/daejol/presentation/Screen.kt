package com.daejol.presentation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon : ImageVector? = null
) {
    data object Home : Screen("home", R.string.home, Icons.Filled.Home)

    data object WorldCupSelection : Screen("worldcup/selection", R.string.worldcup_selection)

    data object WorldCupPlay : Screen("worldcup/play", R.string.worldcup_play)

    data object WorldCupResult : Screen("worldcup/result", R.string.worldcup_result)

    data object AnimalDetail : Screen("animal_detail", R.string.animal_detail)

    data object Matching : Screen("matching", R.string.matching, Icons.Filled.Extension)

    data object Random : Screen("random", R.string.random, Icons.Filled.PhotoLibrary)

    data object MyPage : Screen("my_page", R.string.my_page, Icons.Filled.Person)
}
