package com.daejol.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.daejol.presentation.home.HomeViewModel
import com.daejol.presentation.worldcup.WorldCupViewModel

@Composable
fun homeViewModel(): HomeViewModel = hiltViewModel()

@Composable
fun worldCupViewModel(): WorldCupViewModel = hiltViewModel()

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
) = remember(navController) { AppState(navController) }
