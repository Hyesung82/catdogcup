package com.daejol.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.daejol.presentation.category.match.PersonalWidget
import com.daejol.presentation.home.HomeScreen

@Composable
fun CatDogCupNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
        composable(route = Screen.Matching.route) {
            PersonalWidget()
        }
        composable(route = Screen.Random.route) {
            // TODO: 스토리(랜덤 개냥이 이미지)
        }
        composable(route = Screen.Bookmark.route) {
            // TODO: 북마크
        }
    }
}
