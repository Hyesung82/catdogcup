package com.daejol.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
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
    Scaffold(
        topBar = {
            PopularCatDogTopBar(catDog = catDog)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(TestData.catdogs[0].image)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.sample_cat),
                contentDescription = "인기 개냥이"
            )
            Column(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.space_s))
            ) {
                CatDogDetailAttribute(title = "Weight", desc = catDog.weight)
                CatDogDetailAttribute(title = "Temperament", desc = catDog.temperament.toString())
                CatDogDetailAttribute(title = "Origin", desc = catDog.origin)
                CatDogDetailAttribute(title = "Description", desc = catDog.description)
                CatDogDetailAttribute(title = "Life Span", desc = catDog.lifeSpan)
                CatDogDetailAttribute(title = "Wikipedia URL", desc = catDog.wikipediaUrl)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PopularCatDogTopBar(
    catDog: CatDog
) {
    // TODO: CollapsingToolbarLayout 적용
    TopAppBar(
        title = {
            Row {
                // TODO: 고양이 아이콘 & 강아지 아이콘
//                Image(painter = , contentDescription = )
                Text(text = catDog.name)
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Outlined.Close, contentDescription = "뒤로 가기")
            }
        }
    )
}

@Composable
private fun CatDogDetailAttribute(
    title: String,
    desc: String
) {
    Column {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_l)))
        Text(
            text = title,
            style = Typography.titleLarge
        )
        Text(
            text = desc,
            style = Typography.bodyMedium
        )
    }
}

@Preview
@Composable
fun PopularCatDogDetailScreenPreview() {
    CatdogcupTheme {
        PopularCatDogDetailScreen()
    }
}
