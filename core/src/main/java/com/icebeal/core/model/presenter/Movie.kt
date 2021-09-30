package com.icebeal.core.model.presenter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var id: Int,
    var title: String?,
    var poster: String?,
    var overview: String?,
    var releaseDate: String?,
    var rating: Double?,
    var voter: Int?,
    var isFavorite: Boolean = false
) : Parcelable