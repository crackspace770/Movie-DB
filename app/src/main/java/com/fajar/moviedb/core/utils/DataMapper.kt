package com.fajar.moviedb.core.utils

import com.fajar.moviedb.core.data.source.local.entity.MovieEntity
import com.fajar.moviedb.core.data.source.remote.response.MovieResponse
import com.fajar.moviedb.core.data.source.remote.response.SearchResponse
import com.fajar.moviedb.core.domain.model.Movie

object DataMapper {

    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                releaseDate = it.releaseDate,
                overview = it.overview,
                genres = it.genres,
                runtime = it.runtime,
                tagline = it.tagline,
                voteAverage = it.voteAverage,
                popularity = it.popularity,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                isFavorite = false,
                        isTvShow = it.isTvShow
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                releaseDate = it.releaseDate,
                overview = it.overview,
                genres = it.genres,
                runtime = it.runtime,
                tagline = it.tagline,
                voteAverage = it.voteAverage,
                popularity = it.popularity,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                isFavorite = it.isFavorite,
                isTvShow = it.isTvShow,

            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        title = input.title,
        releaseDate = input.releaseDate,
        overview = input.overview,
        genres = input.genres,
        runtime = input.runtime,
        tagline = input.tagline,
        voteAverage = input.voteAverage,
        popularity = input.popularity,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        isFavorite = input.isFavorite,
        isTvShow = input.isTvShow
    )

    fun mapSearchResponsesToEntities(input: List<SearchResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = if(it.mediaType == "tv") it.name else it.title,
                releaseDate = if(it.mediaType == "tv") it.firstAirDate else it.releaseDate,
                isTvShow = it.mediaType == "tv",
                overview = it.overview,
                tagline = null,
                genres = null,
                runtime = null,
                voteAverage = it.voteAverage,
                popularity = it.popularity,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
            )
            movieList.add(movie)
        }
        return movieList
    }



}