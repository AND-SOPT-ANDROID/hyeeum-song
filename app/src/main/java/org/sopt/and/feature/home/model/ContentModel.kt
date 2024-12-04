package org.sopt.and.feature.home.model

import androidx.annotation.DrawableRes

data class ContentModel (
    @DrawableRes val image : Int,
    val rank : Int? = 0
)
