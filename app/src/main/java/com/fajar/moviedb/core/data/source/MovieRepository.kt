package com.fajar.moviedb.core.data.source


import com.fajar.moviedb.core.data.source.local.LocalDataSource
import com.fajar.moviedb.core.data.source.local.entity.MovieEntity
import com.fajar.moviedb.core.data.source.remote.RemoteDataSource
import com.fajar.moviedb.core.data.source.remote.network.ApiResponse
import com.fajar.moviedb.core.data.source.remote.response.MovieResponse
import com.fajar.moviedb.core.data.source.remote.response.SearchResponse
import com.fajar.moviedb.core.domain.model.Movie
import com.fajar.moviedb.core.domain.repository.IMovieRepository
import com.fajar.moviedb.core.utils.AppExecutor
import com.fajar.moviedb.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutor
    ): IMovieRepository {


    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutor
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localData, appExecutors)
            }
    }


    override fun getPopularMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(){
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getPopularMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
//                data == null || data.isEmpty()
                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
    }.asFlow()

    override fun getSearchMovie(query: String): Flow<Resource<List<Movie>>> {
        return object :
            NetworkBoundResource<List<Movie>, List<SearchResponse>>(){

            override fun shouldFetch(data: List<Movie>?): Boolean =
//                data == null || data.isEmpty()
                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<SearchResponse>>> =
                remoteDataSource.searchMovie(query)

            override suspend fun saveCallResult(data: List<SearchResponse>) {
                val movieList = DataMapper.mapSearchResponsesToEntities(data)
                localDataSource.insertMovie(movieList)

            }

            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getPopularMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

        }.asFlow()
    }




    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTourism(tourismEntity, state) }
    }

}