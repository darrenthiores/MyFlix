package com.icebeal.core.data.repository

import com.icebeal.core.NetworkBoundResource
import com.icebeal.core.data.local.LocalDataSource
import com.icebeal.core.data.remote.ApiResponse
import com.icebeal.core.data.remote.RemoteDataSource
import com.icebeal.core.model.data.response.MovieResponse
import com.icebeal.core.model.domain.MovieDomain
import com.icebeal.core.utils.AppExecutors
import com.icebeal.core.utils.DataMapper
import com.icebeal.core.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getMovie(): Flow<Resource<List<MovieDomain>>> =
        object : NetworkBoundResource<List<MovieDomain>, List<MovieResponse>>() {

            override fun loadFromDB(): Flow<List<MovieDomain>> =
                localDataSource.getMovie().map { DataMapper.mapEntitiesToDomain(it) }

            override fun shouldFetch(data: List<MovieDomain>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieEntities = DataMapper.mapResponsesToEntities(data)
                localDataSource.insert(movieEntities)
            }

        }.asFlow()


    override fun getFavoriteMovie(): Flow<List<MovieDomain>> =
        localDataSource.getFavoriteMovie().map { DataMapper.mapEntitiesToDomain(it) }

    override fun setMovieFavorite(movie: MovieDomain, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movieEntity, state) }
    }

    override fun searchMovie(searchQuery: String): Flow<List<MovieDomain>> =
        localDataSource.searchMovie(searchQuery).map { DataMapper.mapEntitiesToDomain(it) }

}