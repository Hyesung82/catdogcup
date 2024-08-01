package com.daejol.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.daejol.domain.usecase.CheckAuth
import com.daejol.domain.usecase.GetRankingUseCase
import com.daejol.presentation.ui.theme.CatdogcupTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var checkAuth: CheckAuth
    @Inject lateinit var getRankingUseCase: GetRankingUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CatdogcupTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch {
            checkAuth.checkIfUserIsSignedIn()
            getRankingUseCase.getPopularCatsAndDogs()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CatdogcupTheme {
        MainApp()
    }
}
