package com.daejol.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.daejol.presentation.R
import com.daejol.presentation.data.CatDog
import com.daejol.presentation.data.TestData
import com.daejol.presentation.ui.theme.CatdogcupTheme
import com.daejol.presentation.ui.theme.Typography

@Composable
fun PopularCatDogDetailScreen(
    catDog: CatDog = TestData.catdogs[0]
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            CatDogDetailImage()
            CatDogDetailDescription(catDog)
        }
    }
}

@Composable
private fun CatDogDetailImage() {
    Box(modifier = Modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(TestData.catdogs[0].image)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.sample_cat),
            contentDescription = stringResource(id = R.string.popular_catdog_image)
        )
        PopularCatDogTopBar()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PopularCatDogTopBar() {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = { /* TODO: 뒤로 가기 */ }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription = stringResource(id = R.string.popular_catdog_back)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        modifier = Modifier
    )
}

@Composable
private fun CatDogDetailDescription(
    catDog: CatDog
) {
    Column(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.space_m))
    ) {
        Row {
            Text(
                text = catDog.name,
                style = Typography.headlineLarge,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
            IconButton(onClick = { /* TODO: 좋아요 */ }) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = stringResource(id = R.string.popular_catdog_like)
                )
            }
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_l)))
        CatDogDetailAttribute(title = "Weight", desc = catDog.weight)
        CatDogDetailAttribute(title = "Temperament", desc = catDog.temperament.toString())
        CatDogDetailAttribute(title = "Origin", desc = catDog.origin)
        CatDogDetailAttribute(title = "Description", desc = catDog.description)
        CatDogDetailAttribute(title = "Life Span", desc = catDog.lifeSpan)
        CatDogDetailAttribute(title = "Wikipedia URL", desc = catDog.wikipediaUrl)
    }
}

@Composable
private fun CatDogDetailAttribute(
    title: String,
    desc: String
) {
    Column {
        Text(
            text = title,
            style = Typography.titleLarge
        )
        Text(
            text = desc,
            style = Typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_l)))
    }
}

@Preview
@Composable
fun PopularCatDogDetailScreenPreview() {
    CatdogcupTheme {
        PopularCatDogDetailScreen()
    }
}
