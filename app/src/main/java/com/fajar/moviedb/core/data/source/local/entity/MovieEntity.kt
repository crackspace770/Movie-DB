package com.fajar.moviedb.core.data.source.local.entity


import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "MovieEntities")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "releaseDate")
    val releaseDate: String?,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "genres")
    val genres: String?,

    @ColumnInfo(name = "runtime")
    val runtime: Int?,

    @ColumnInfo(name = "tagline")
    val tagline: String?,

    @ColumnInfo(name = "voteAverage")
    val voteAverage: Double,

    @ColumnInfo(name = "popularity")
    val popularity: Double,

    @ColumnInfo(name = "posterPath")
    val posterPath: String?,

    @ColumnInfo(name="backdropPath")
    val backdropPath: String?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,

    @ColumnInfo(name = "isTvShow")
    var isTvShow: Boolean = false

): Parcelable
