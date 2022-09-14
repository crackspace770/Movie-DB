package com.fajar.moviedb.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    val id: Int?,
    val title: String?,
    val releaseDate: String?,
    val overview: String?,
    val tagline: String?,
    val genres: String?,
    val runtime: Int?,
    val voteAverage: Double,
    val popularity: Double,
    val posterPath: String?,
    val backdropPath: String?,
    var isFavorite: Boolean = false,
    var isTvShow: Boolean = false
): Parcelable




