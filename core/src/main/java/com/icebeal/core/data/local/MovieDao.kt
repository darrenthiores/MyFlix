package com.icebeal.core.data.local

import androidx.room.*
import com.icebeal.core.model.data.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: List<MovieEntity>)

    @Update
    fun setMovieFavorite(movie: MovieEntity)

    @Query("SELECT * FROM movieEntity")
    fun getMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieEntity WHERE isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieEntity WHERE title LIKE :searchQuery")
    fun searchMovie(searchQuery: String): Flow<List<MovieEntity>>

}