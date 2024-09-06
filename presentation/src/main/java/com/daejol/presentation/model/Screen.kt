package com.daejol.presentation.model

sealed class Screen(
    val route: String
) {
    data object Home : Screen("home")

    data object WorldCupSelection : Screen("world_cup/selection")

    data object WorldCupPlay : Screen("world_cup/play")

    data object WorldCupResult : Screen("world_cup/result")

    data object AnimalDetail : Screen("animal_detail")

    data object MyPage : Screen("my_page")

    data object Bookmark : Screen("bookmark")
}
