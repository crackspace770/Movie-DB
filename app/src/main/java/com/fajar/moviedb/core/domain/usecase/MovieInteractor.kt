package com.fajar.moviedb.core.domain.usecase

import com.fajar.moviedb.core.domain.model.Movie
import com.fajar.moviedb.core.domain.repository.IMovieRepository
import javax.inject.Inject

class MovieInteractor @Inject constructor (private val movieRepository: IMovieRepository): MovieUseCase {

    override fun getPopularMovie() = movieRepository.getPopularMovie()

    override fun getPopularTv() = movieRepository.getPopularTv()

    override fun getSearchMovie(query: String) = movieRepository.getSearchMovie(query)

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(tourism: Movie, state: Boolean) = movieRepository.setFavoriteMovie(tourism, state)



}