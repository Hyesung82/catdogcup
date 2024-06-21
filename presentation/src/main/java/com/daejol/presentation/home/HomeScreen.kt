package com.daejol.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daejol.presentation.R
import com.daejol.presentation.ui.theme.Orange80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.catdogcup_logo),
                            contentDescription = stringResource(R.string.home_app_bar_title),
                            colorFilter = ColorFilter.tint(Orange80),
                            modifier = Modifier
                                .width(64.dp)
                                .fillMaxHeight()
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: 품종 검색 */ }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = stringResource(R.string.home_app_bar_search)
                        )
                    }
                    IconButton(onClick = { /* TODO: 마이페이지 */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = stringResource(R.string.home_app_bar_my_page)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(dimensionResource(id = R.dimen.space_m))
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CatOfTodayCard()
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_m)))
            WorldCupCard(
                R.string.cat_world_cup_title,
                R.string.cat_world_cup_desc,
                R.string.cat_world_cup_button,
                R.drawable.cat
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_m)))
            WorldCupCard(
                R.string.dog_world_cup_title,
                R.string.dog_world_cup_desc,
                R.string.dog_world_cup_button,
                R.drawable.dog
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_m)))
            WorldCupCard(
                R.string.mixed_world_cup_title,
                R.string.mixed_world_cup_desc,
                R.string.mixed_world_cup_button,
                R.drawable.catdog
            )
            // TODO: margin 수정
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_s)))
            PopularCatDog()
        }
    }
}
