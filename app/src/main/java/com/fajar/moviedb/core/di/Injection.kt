package com.fajar.moviedb.core.di

import android.content.Context
import com.fajar.moviedb.core.data.source.MovieRepository
import com.fajar.moviedb.core.data.source.local.room.MovieDatabase
import com.fajar.moviedb.core.data.source.remote.network.ApiConfig
import com.fajar.moviedb.core.domain.usecase.MovieInteractor
import com.fajar.moviedb.core.domain.usecase.MovieUseCase
import com.fajar.moviedb.core.data.source.local.LocalDataSource
import com.fajar.moviedb.core.data.source.remote.RemoteDataSource
import com.fajar.moviedb.core.domain.repository.IMovieRepository
import com.fajar.moviedb.core.utils.AppExecutor

object Injection {

    private fun provideRepository(context: Context): IMovieRepository {
        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.tourismDao())
        val appExecutors = AppExecutor()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideMovieUseCase(context: Context): MovieUseCase {
        val repository = provideRepository(context)
        return MovieInteractor(repository)
    }
}