package com.daejol.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.daejol.presentation.Screen
import com.daejol.presentation.category.match.PersonalWidget
import com.daejol.presentation.home.HomeScreen
import com.daejol.presentation.worldcup.play.WorldCupPlayScreen
import com.daejol.presentation.worldcup.result.WorldCupResultScreen
import com.daejol.presentation.worldcup.selection.WorldCupScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    val worldCupViewModel = worldCupViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.Category.route) {
            PersonalWidget()
        }
        composable(route = Screen.Bookmark.route) {
            // TODO: 북마크
        }
        composable(route = Screen.MyPage.route) {
            // TODO: 마이페이지
        }
        composable(route = Screen.WorldCupSelection.route) {
            WorldCupScreen(
                viewModel = worldCupViewModel,
                type = "CAT",
                navController = navController
            )
        }
        composable(route = Screen.WorldCupPlay.route) {
            WorldCupPlayScreen(
                viewModel = worldCupViewModel,
                navController = navController
            )
        }
        composable(route = Screen.WorldCupResult.route) {
            WorldCupResultScreen(
                viewModel = worldCupViewModel,
                navController = navController
            )
        }
    }
}