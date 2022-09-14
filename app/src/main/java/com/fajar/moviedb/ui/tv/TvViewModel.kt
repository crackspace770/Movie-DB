package com.fajar.moviedb.ui.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fajar.moviedb.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {

    val tv = movieUseCase.getPopularTv().asLiveData()


}