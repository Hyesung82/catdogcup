package com.daejol.presentation.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.daejol.presentation.R
import com.daejol.presentation.ui.theme.CatdogcupTheme
import com.daejol.presentation.ui.theme.Typography

@Composable
fun EtcContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = dimensionResource(id = R.dimen.space_l))
    ) {
        Text(
            text = "기타",
            style = Typography.titleLarge
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_m)))
        Text(
            text = "로그아웃",
            style = Typography.bodyMedium
        )
    }
}

@Preview
@Composable
fun EtcContentPreview() {
    CatdogcupTheme {
        EtcContent(
            Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
        )
    }
}
