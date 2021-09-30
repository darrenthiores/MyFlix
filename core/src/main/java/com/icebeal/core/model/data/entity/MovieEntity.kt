package com.icebeal.core.model.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieEntity")
data class MovieEntity(

    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var title: String?,
    var poster: String?,
    var overview: String?,
    var releaseDate: String?,
    var rating: Double?,
    var voter: Int?,
    var isFavorite: Boolean = false

)
