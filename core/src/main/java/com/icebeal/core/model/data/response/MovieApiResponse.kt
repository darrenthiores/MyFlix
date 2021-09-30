package com.icebeal.core.model.data.response

import com.google.gson.annotations.SerializedName

data class MovieApiResponse(

    @SerializedName("results")
    var movies: List<MovieResponse>

)