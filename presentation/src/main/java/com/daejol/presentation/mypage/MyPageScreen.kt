package com.daejol.presentation.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.daejol.presentation.R
import com.daejol.presentation.data.SampleData
import com.daejol.presentation.ui.theme.CatdogcupTheme

@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier,
    navController: NavController?
) {
    Column(
        modifier = modifier
            .padding(vertical = dimensionResource(id = R.dimen.space_l))
            .verticalScroll(rememberScrollState())
    ) {
        ProfileContent(imageUrl = SampleData.imageUrls[0])
        Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.space_xl)))
        BookmarkContent(
            navController = navController,
            imageUrls = SampleData.imageUrls.subList(0, 10)
        )
        Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.space_xl)))
        AchievementContent()
        Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.space_xl)))
        EtcContent()
        Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.space_xl)))
    }
}

@Preview
@Composable
fun MyPageScreenPreview() {
    CatdogcupTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            MyPageScreen(
                modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
                navController = null
            )
        }
    }
}
