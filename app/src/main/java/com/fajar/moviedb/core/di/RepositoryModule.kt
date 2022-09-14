package com.fajar.moviedb.core.di

import com.fajar.moviedb.core.data.source.MovieRepository
import com.fajar.moviedb.core.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(tourismRepository: MovieRepository): IMovieRepository

}