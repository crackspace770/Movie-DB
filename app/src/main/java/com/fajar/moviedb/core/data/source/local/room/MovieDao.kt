package com.fajar.moviedb.core.data.source.local.room

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.fajar.moviedb.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieEntities")
    fun getPopularMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM MovieEntities where isFavorite = 1")
    fun getFavoriteTourism(): Flow<List<MovieEntity>>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(tourism: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(tourism: MovieEntity)
}