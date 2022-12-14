package com.fajar.moviedb.core.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val POPULAR = "Popular"
    const val LATEST = "Latest"
    const val OLDEST = "Oldest"
    const val BEST = "Best"
    const val WORST = "Worst"
    const val RANDOM = "Random"

    fun getSortedQuery(filter: String, isTvShow: Int): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM MovieEntities WHERE isTvShow = $isTvShow ")
        when (filter) {
            POPULAR -> simpleQuery.append("ORDER BY popularity DESC")
            LATEST -> simpleQuery.append("ORDER BY releaseDate DESC")
            OLDEST -> simpleQuery.append("ORDER BY releaseDate ASC")
            BEST -> simpleQuery.append("ORDER BY voteAverage DESC")
            WORST -> simpleQuery.append("ORDER BY voteAverage ASC")
            RANDOM -> simpleQuery.append("ORDER BY RANDOM()")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}