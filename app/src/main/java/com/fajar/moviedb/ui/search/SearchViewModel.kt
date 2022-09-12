package com.fajar.moviedb.ui.search


import androidx.lifecycle.*
import com.fajar.moviedb.core.data.source.Resource
import com.fajar.moviedb.core.domain.model.Movie
import com.fajar.moviedb.core.domain.usecase.MovieUseCase

class SearchViewModel(private val movieUseCase: MovieUseCase): ViewModel() {

    private val listMovieMutable = MutableLiveData<ArrayList<Movie>>()
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    private val searchQuery = MutableLiveData<String>()

    fun setSearchQuery(query: String) {
        this.searchQuery.value = query
    }

    var searchResult: LiveData<Resource<List<Movie>>> =
        Transformations.switchMap(searchQuery) { query ->
            movieUseCase.getSearchMovie(query).asLiveData()
        }






}