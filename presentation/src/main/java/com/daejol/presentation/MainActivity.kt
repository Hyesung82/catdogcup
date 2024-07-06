package com.daejol.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.daejol.presentation.home.HomeScreen
import com.daejol.presentation.category.match.PersonalWidget
import com.daejol.presentation.ui.theme.CatdogcupTheme
import com.daejol.presentation.worldcup.WorldCupViewModel
import com.daejol.presentation.worldcup.play.WorldCupPlayScreen
import com.daejol.presentation.worldcup.selection.WorldCupScreen
import dagger.hilt.android.AndroidEntryPoint
import usecase.WorldCupType

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CatdogcupTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CatDogCupApp()
//                    WorldCupScreen(
//                        type = WorldCupType.CAT
//                    )
//                    WorldCupPlayScreen()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
//        CoroutineScope(Dispatchers.IO).launch {
//            getImageUsecase.getRandomCatImages(10)?.collect {
//                it?.forEach { list ->
//                    println("[keykat] list: $list")
//                }
//            }
//        }
    }
}

@Composable
fun CatDogCupApp() {
    val items = listOf(
        Screen.Home,
        Screen.Category,
        Screen.Bookmark,
        Screen.MyPage,
    )
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            screen.icon?.let {
                                Icon(
                                    screen.icon,
                                    contentDescription = null
                                )
                            }
                        },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        CatDogCupNavHost(navController, Modifier.padding(innerPadding))
    }
}

@Composable
fun CatDogCupNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    val viewModel: WorldCupViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
        composable(route = Screen.Category.route) {
            PersonalWidget()
        }
        composable(route = Screen.Bookmark.route) {
            // TODO: 북마크
        }
        composable(route = Screen.MyPage.route) {
            WorldCupScreen(viewModel = viewModel, navController = navController, type = "CAT")
        }
        composable(route = Screen.WorldCupSelection.route) {
            WorldCupScreen(viewModel = viewModel, navController = navController, type = "CAT")
        }
        composable(route = Screen.WorldCupPlay.route) { backStackEntry ->
            WorldCupPlayScreen(viewModel = viewModel)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CatdogcupTheme {
//        CatDogCupApp()
    }
}
