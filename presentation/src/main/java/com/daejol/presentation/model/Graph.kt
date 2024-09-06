package com.daejol.presentation.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.ui.graphics.vector.ImageVector
import com.daejol.presentation.R

sealed class Graph(
    val route: String,
    @StringRes val resourceId: Int,
    val icon : ImageVector? = null
) {
    data object Home : Graph("home", R.string.home, Icons.Filled.Home)

    data object WorldCupSelection : Graph("worldcup/selection", R.string.worldcup_selection)

    data object WorldCupPlay : Graph("worldcup/play", R.string.worldcup_play)

    data object WorldCupResult : Graph("worldcup/result", R.string.worldcup_result)

    data object Matching : Graph("matching", R.string.matching, Icons.Filled.Extension)

    data object MatchingQuestion : Graph("matching_question", R.string.matching_question, Icons.Filled.Extension)

    data object MatchingLoading : Graph("matching_loading", R.string.matching, Icons.Filled.Extension)

    data object MatchingResult : Graph("matching_result", R.string.matching, Icons.Filled.Extension)

    data object Story : Graph("story", R.string.story, Icons.Filled.PhotoLibrary)

    data object MyPage : Graph("my_page_graph", R.string.my_page, Icons.Filled.Person)
}
