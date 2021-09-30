package com.icebeal.core.model.domain

data class MovieDomain(

    var id: Int,
    var title: String?,
    var poster: String?,
    var overview: String?,
    var releaseDate: String?,
    var rating: Double?,
    var voter: Int?,
    var isFavorite: Boolean = false

)