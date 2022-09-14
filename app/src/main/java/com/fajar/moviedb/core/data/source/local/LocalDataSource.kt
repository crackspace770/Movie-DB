package com.fajar.moviedb.core.data.source.local

import com.fajar.moviedb.core.data.source.local.entity.MovieEntity
import com.fajar.moviedb.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

//    companion object {
//        private var instance: LocalDataSource? = null

 //       fun getInstance(tourismDao: MovieDao): LocalDataSource =
 //           instance ?: synchronized(this) {
 //               instance ?: LocalDataSource(tourismDao)
 //           }
 //   }

    fun getPopularMovie(): Flow<List<MovieEntity>> = movieDao.getPopularMovies()

    fun getPopularTv(): Flow<List<MovieEntity>> = movieDao.getPopularTv()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteTourism()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}