package com.fajar.moviedb.core.data.source.remote.network

import com.fajar.moviedb.core.data.source.remote.response.ListMovieResponse
import com.fajar.moviedb.core.data.source.remote.response.ListSearchResponse
import com.fajar.moviedb.core.data.source.remote.response.ListTvResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey: String
    ): ListMovieResponse

    @GET("search/multi")
   suspend fun getSearchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean
    ): ListSearchResponse

    @GET("tv/popular")
    suspend fun getPopularTvShowList(
        @Query("api_key") apiKey: String
    ): ListTvResponse
}