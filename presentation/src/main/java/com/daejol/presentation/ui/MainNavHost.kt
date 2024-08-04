package com.daejol.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.daejol.presentation.model.Screen
import com.daejol.presentation.ui.bookmark.BookmarkScreen
import com.daejol.presentation.ui.match.MatchStartScreen
import com.daejol.presentation.ui.home.HomeScreen
import com.daejol.presentation.ui.home.PopularAnimalDetailScreen
import com.daejol.presentation.ui.match.MatchLoadingScreen
import com.daejol.presentation.ui.match.MatchQuestionScreen
import com.daejol.presentation.ui.mypage.MyPageScreen
import com.daejol.presentation.ui.worldcup.play.WorldCupPlayScreen
import com.daejol.presentation.ui.worldcup.result.WorldCupResultScreen
import com.daejol.presentation.ui.worldcup.selection.WorldCupScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    val worldCupViewModel = worldCupViewModel()
    val homeViewModel = homeViewModel()

    val homeUiState by homeViewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                navController = navController,
                onDetailButtonClicked = {
                    homeViewModel.setBreed(it)
                    navController.navigate(Screen.AnimalDetail.route)
                }
            )
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
        composable(route = Screen.AnimalDetail.route) {
            PopularAnimalDetailScreen(
                navController = navController,
                homeUiState = homeUiState
            )
        }

        composable(route = Screen.Matching.route) {
            MatchStartScreen(
                navController = navController
            )
        }

        composable(route = Screen.MatchingQuestion.route) {
            MatchQuestionScreen(
                navController = navController
            )
        }

        composable(route = Screen.MatchingLoading.route) {
            MatchLoadingScreen()
        }

        composable(route = Screen.Story.route) {
            // TODO: 스토리 화면
        }

        composable(route = Screen.MyPage.route) {
            MyPageScreen(navController = navController)
        }
        composable(route = Screen.Bookmark.route) {
            BookmarkScreen()
        }
    }
}
