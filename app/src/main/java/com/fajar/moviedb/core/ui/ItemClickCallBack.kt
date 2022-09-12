package com.fajar.moviedb.core.ui


import com.fajar.moviedb.core.domain.model.Movie


interface ItemClickCallBack {
    fun onItemClicked(movieItem: Movie)
}