package com.daejol.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.daejol.presentation.R
import com.daejol.presentation.model.Animal
import com.daejol.presentation.data.SampleData
import com.daejol.presentation.ui.theme.CatdogcupTheme
import com.daejol.presentation.ui.theme.Pretendard
import com.daejol.presentation.ui.theme.Red100
import com.daejol.presentation.ui.theme.White100

@Composable
fun PopularAnimalCard(
    ranking: Int,
    animal: Animal,
    onClick: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.elevation_default)
        ),
//        colors = CardDefaults.cardColors(
//            containerColor = White100
//        )
        onClick = onClick
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(animal.imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.sample_cat),
                contentDescription = stringResource(R.string.popular_animal_image),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
            Box(
                modifier = Modifier.padding(12.dp)
            ) {
                Ranking(ranking)
            }
        }
    }
}

@Composable
private fun Ranking(
    ranking: Int = 1
) {
    Text(
        text = ranking.toString(),
        fontSize = dimensionResource(id = R.dimen.text_xs).value.sp,
        fontFamily = Pretendard,
        fontWeight = FontWeight.SemiBold,
        color = White100,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .size(dimensionResource(id = R.dimen.popular_animal_ranking_size))
            .drawBehind {
                drawCircle(
                    color = Red100,
                    radius = this.size.maxDimension / 2f
                )
            }
    )
}

@Preview
@Composable
fun PopularCatDogCardPreview() {
    CatdogcupTheme {
        PopularAnimalCard(
            ranking = 1,
            animal = SampleData.animals[0],
            onClick = {}
        )
    }
}
