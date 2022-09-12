package com.fajar.moviedb.core.domain.usecase

import com.fajar.moviedb.core.data.source.Resource
import com.fajar.moviedb.core.domain.model.Movie
import com.fajar.moviedb.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val tourismRepository: IMovieRepository): MovieUseCase {

    override fun getPopularMovie() = tourismRepository.getPopularMovie()

    override fun getSearchMovie(query: String) = tourismRepository.getSearchMovie(query)

    override fun getFavoriteMovie() = tourismRepository.getFavoriteMovie()

    override fun setFavoriteMovie(tourism: Movie, state: Boolean) = tourismRepository.setFavoriteMovie(tourism, state)



}