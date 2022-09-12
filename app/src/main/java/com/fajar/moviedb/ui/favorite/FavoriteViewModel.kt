package com.fajar.moviedb.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fajar.moviedb.core.domain.usecase.MovieUseCase

class FavoriteViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
}