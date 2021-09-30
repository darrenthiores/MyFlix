package com.icebeal.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.icebeal.core.model.data.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDb : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}