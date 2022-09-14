package com.fajar.moviedb.core.data.source.remote

import android.util.Log
import com.fajar.moviedb.core.data.source.remote.network.ApiResponse
import com.fajar.moviedb.core.data.source.remote.network.ApiService
import com.fajar.moviedb.core.data.source.remote.response.MovieResponse
import com.fajar.moviedb.core.data.source.remote.response.SearchResponse
import com.fajar.moviedb.core.data.source.remote.response.TvResponse
import com.fajar.moviedb.core.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    private val apiKey = Constant.API_KEY

    suspend fun getMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getPopularMovie(apiKey)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty(response.results))
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTv(): Flow<ApiResponse<List<TvResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getPopularTvShowList(apiKey)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty(response.results))
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchMovie(query:String): Flow<ApiResponse<List<SearchResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getSearchMovie(apiKey, query, false)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty(response.results))
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }


}