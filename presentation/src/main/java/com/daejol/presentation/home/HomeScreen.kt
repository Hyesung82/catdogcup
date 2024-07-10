package com.daejol.presentation.home

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.daejol.presentation.R
import com.daejol.presentation.ui.theme.CatdogcupTheme
import com.daejol.presentation.ui.theme.Orange100
import com.daejol.presentation.ui.theme.Typography

private val catdogs = listOf(
    Catdog("Russian Blue", 1, "https://cdn2.thecatapi.com/images/cqg.jpg"),
    Catdog("Keykat", 2, "https://cdn2.thecatapi.com/images/ebv.jpg"),
    Catdog("Snow Cat", 3, "https://cdn.thedogapi.com/images/Hylo4Snaf.jpeg"),
    Catdog("Grasskitty", 4, "https://cdn2.thecatapi.com/images/xxBaNrfM0.jpg"),
    Catdog("Russian Blue", 1, "https://cdn2.thecatapi.com/images/cqg.jpg"),
    Catdog("Keykat", 2, "https://cdn2.thecatapi.com/images/ebv.jpg"),
    Catdog("Snow Cat", 3, "https://cdn.thedogapi.com/images/Hylo4Snaf.jpeg"),
    Catdog("Grasskitty", 4, "https://cdn2.thecatapi.com/images/xxBaNrfM0.jpg"),
    Catdog("Russian Blue", 1, "https://cdn2.thecatapi.com/images/cqg.jpg"),
    Catdog("Keykat", 2, "https://cdn2.thecatapi.com/images/ebv.jpg")
)

@Composable
fun HomeScreen(
    navController: NavController? = null
) {
    Scaffold(
        topBar = {
            HomeAppBar()
        }
    ) { innerPadding ->
        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive((screenWidth / 2 - 22).dp),
            verticalItemSpacing = dimensionResource(id = R.dimen.space_s),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_s)),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = dimensionResource(id = R.dimen.space_m))
        ) {
            item(span = StaggeredGridItemSpan.FullLine) {
                Column {
            WorldCupContent(navController)
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_m)))
                    Title(text = R.string.popular_catdog_title)
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_xs)))
                }
            }
            itemsIndexed(catdogs) { i, catdog ->
                PopularCatDogCard(i + 1, catdog, (screenWidth / 2 - 22).dp)
            }
            item(span = StaggeredGridItemSpan.FullLine) {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_m)))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeAppBar() {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.catdogcup_logo),
                    contentDescription = stringResource(R.string.home_app_bar_title),
                    colorFilter = ColorFilter.tint(Orange100),
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.home_app_bar_title_width))
                        .fillMaxHeight()
                )
            }
        },
        actions = {
            IconButton(onClick = { /* TODO: 품종 검색 */ }) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = stringResource(R.string.home_app_bar_search),
                    tint = Orange100
                )
            }
            IconButton(onClick = { /* TODO: 마이페이지 */ }) {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = stringResource(R.string.home_app_bar_my_page),
                    tint = Orange100
                )
            }
        }
    )
}

@Composable
fun Title(
    @StringRes text: Int
) {
    Text(
        text = stringResource(id = text),
        style = Typography.titleLarge,
        modifier = Modifier
    )
}

@Preview
@Composable
fun HomeScreenPreview() {
    CatdogcupTheme {
        HomeScreen()
    }
}

data class Catdog(
    val name: String,
    val ranking: Int,
    val image: String
)
