package com.fajar.moviedb.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fajar.moviedb.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val tourism = movieUseCase.getPopularMovie().asLiveData()


}