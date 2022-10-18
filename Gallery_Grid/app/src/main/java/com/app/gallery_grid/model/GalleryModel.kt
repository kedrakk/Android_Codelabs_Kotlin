package com.app.gallery_grid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val title:Int,
    val resCount:Int,
    @DrawableRes val imageResource:Int
    )
