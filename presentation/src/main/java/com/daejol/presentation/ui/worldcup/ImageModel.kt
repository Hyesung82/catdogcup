package com.daejol.presentation.ui.worldcup

import coil.request.ImageRequest
import com.daejol.domain.entity.ImageEntity

data class ImageModel(
    val imageEntity: ImageEntity,
    val imageRequest: ImageRequest,
)
