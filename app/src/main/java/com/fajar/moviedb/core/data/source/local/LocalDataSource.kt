package com.fajar.moviedb.core.data.source.local

import com.fajar.moviedb.core.data.source.local.entity.MovieEntity
import com.fajar.moviedb.core.data.source.local.room.MovieDao
import com.fajar.moviedb.core.domain.model.Movie
import com.fajar.moviedb.core.utils.SortUtils
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val movieDao: MovieDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(tourismDao: MovieDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(tourismDao)
            }
    }

    fun getPopularMovie(): Flow<List<MovieEntity>> = movieDao.getPopularMovies()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteTourism()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteTourism(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}