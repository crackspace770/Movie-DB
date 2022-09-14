package com.fajar.moviedb.core.domain.repository

import com.fajar.moviedb.core.data.source.Resource
import com.fajar.moviedb.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getPopularMovie(): Flow<Resource<List<Movie>>>

    fun getPopularTv():Flow<Resource<List<Movie>>>

   fun getSearchMovie(query: String) : Flow<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

}