package com.daejol.presentation.model

sealed class Screen(
    val route: String
) {
    data object AnimalDetail : Screen("animal_detail")

    data object MyPage : Screen("my_page")

    data object Bookmark : Screen("bookmark")
}
