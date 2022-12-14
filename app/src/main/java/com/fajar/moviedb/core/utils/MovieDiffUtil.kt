package com.fajar.moviedb.core.utils

import androidx.recyclerview.widget.DiffUtil
import com.fajar.moviedb.core.domain.model.Movie

class MovieDiffUtil(
    private val oldList: List<Movie>,
    private val newList: List<Movie>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> false
            oldList[oldItemPosition].title != newList[newItemPosition].title -> false
            oldList[oldItemPosition].overview != newList[newItemPosition].overview -> false
            oldList[oldItemPosition].releaseDate != newList[newItemPosition].releaseDate -> false
            oldList[oldItemPosition].voteAverage != newList[newItemPosition].voteAverage -> false
            oldList[oldItemPosition].tagline != newList[newItemPosition].tagline -> false
            oldList[oldItemPosition].genres != newList[newItemPosition].genres -> false
            oldList[oldItemPosition].runtime != newList[newItemPosition].runtime -> false
            oldList[oldItemPosition].posterPath != newList[newItemPosition].posterPath -> false
            //oldList[oldItemPosition].backdropPath != newList[newItemPosition].backdropPath -> false
            else -> true
        }
    }
}