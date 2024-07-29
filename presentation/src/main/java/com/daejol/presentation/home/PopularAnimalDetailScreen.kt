package com.daejol.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.daejol.presentation.R
import com.daejol.presentation.data.Animal
import com.daejol.presentation.data.TestData
import com.daejol.presentation.ui.theme.CatdogcupTheme
import com.daejol.presentation.ui.theme.Typography

@Composable
fun PopularAnimalDetailScreen(
    animal: Animal = TestData.animals[0]
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            AnimalDetailImage(innerPadding)
            AnimalDetailDescription(animal)
        }
    }
}

@Composable
private fun AnimalDetailImage(
    innerPadding: PaddingValues
) {
    Box(modifier = Modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(TestData.animals[0].image)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.sample_cat),
            contentDescription = stringResource(id = R.string.popular_animal_image),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
        PopularAnimalTopBar()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PopularAnimalTopBar() {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = { /* TODO: 뒤로 가기 */ }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription = stringResource(id = R.string.popular_animal_back)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        modifier = Modifier
    )
}

@Composable
private fun AnimalDetailDescription(
    animal: Animal
) {
    Column(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.space_m))
    ) {
        Row {
            Text(
                text = animal.name,
                style = Typography.headlineLarge,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
            IconButton(onClick = { /* TODO: 좋아요 */ }) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = stringResource(id = R.string.popular_animal_like)
                )
            }
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_l)))
        AnimalDetailAttribute(title = "Weight", desc = animal.weight)
        AnimalDetailAttribute(title = "Temperament", desc = animal.temperament.toString())
        AnimalDetailAttribute(title = "Origin", desc = animal.origin)
        AnimalDetailAttribute(title = "Description", desc = animal.description)
        AnimalDetailAttribute(title = "Life Span", desc = animal.lifeSpan)
        AnimalDetailAttribute(title = "Wikipedia URL", desc = animal.wikipediaUrl)
    }
}

@Composable
private fun AnimalDetailAttribute(
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
fun PopularAnimalDetailScreenPreview() {
    CatdogcupTheme {
        PopularAnimalDetailScreen()
    }
}
