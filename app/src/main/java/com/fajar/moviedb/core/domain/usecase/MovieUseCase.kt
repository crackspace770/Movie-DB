package com.fajar.moviedb.core.domain.usecase

import com.fajar.moviedb.core.data.source.Resource
import com.fajar.moviedb.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getPopularMovie(): Flow<Resource<List<Movie>>>
    fun getSearchMovie(query: String):Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(tourism: Movie, state: Boolean)
}