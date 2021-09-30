package com.icebeal.core.model.data.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("poster_path")
    var poster: String? = null,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("release_date")
    var releaseDate: String? = null,
    @SerializedName("vote_average")
    var rating: Double? = 0.0,
    @SerializedName("vote_count")
    var voter: Int? = 0

)
