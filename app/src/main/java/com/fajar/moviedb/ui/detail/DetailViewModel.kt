package com.fajar.moviedb.ui.detail

import androidx.lifecycle.ViewModel
import com.fajar.moviedb.core.domain.model.Movie
import com.fajar.moviedb.core.domain.usecase.MovieUseCase


class DetailViewModel(private val movieUseCase: MovieUseCase): ViewModel() {

    fun setFavoriteTourism(movie: Movie, newStatus:Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)

}