package com.daejol.presentation.worldcup

import coil.request.ImageRequest
import entity.ImageEntity

data class ImageModel(
    val imageEntity: ImageEntity,
    val imageRequest: ImageRequest,
)