package com.fajar.moviedb.ui.detail

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fajar.moviedb.R
import com.fajar.moviedb.core.domain.model.Movie
import com.fajar.moviedb.core.ui.ViewModelFactory
import com.fajar.moviedb.core.utils.Constant.Companion.IMAGE_BASE_URL
import com.fajar.moviedb.databinding.ActivityDetailBinding
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity: AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var detailTourismViewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setSupportActionBar(binding.toolbar)

        val factory = ViewModelFactory.getInstance(this)
        detailTourismViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val detailTourism = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showDetailTourism(detailTourism)
    }

    private fun showDetailTourism(detailTourism: Movie?) {
        binding.apply {
            detailTourism?.apply {
                supportActionBar?.title = title
                tvTitle.text= title
                tvDescription.text = overview
                tvGenre.text=  if (genres == null || genres == "") getString(R.string.no_genre_data) else genres
                tvRating.text= voteAverage.toString()
                tvPopularity.text= popularity.toString()

                ivDetailImage.loadImage("${IMAGE_BASE_URL}${backdropPath}")


                if (releaseDate == null || releaseDate == "") {
                    tvRelease.text = getString(R.string.no_data)
                } else {
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                    val date = releaseDate.let { dateFormat.parse(it) }
                    if (date != null) {
                        val dateFormatted =
                            SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH).format(date)
                        tvRelease.text = dateFormatted
                    } else {
                        tvRelease.text = getString(R.string.no_data)
                    }
                }

                val category = if (isTvShow) "TV SHOW" else "MOVIE"

                tvRuntime.text = getString(R.string.category, category)
                if (runtime != null) {
                    if (category == "MOVIE") {
                        val hours = runtime.div(60)
                        val minutes = runtime.rem(60)
                        val duration = "${hours}h ${minutes}m"
                       tvRuntime.text = duration
                    } else {
                        tvRuntime.text = getString(R.string.episodeRuntime, runtime)
                    }
                } else {
                    tvRuntime.text = getString(R.string.no_data)
                }

                var statusFavorite = isFavorite
                setStatusFavorite(statusFavorite)
                fab.setOnClickListener {
                    statusFavorite = !statusFavorite
                    detailTourismViewModel.setFavoriteTourism(detailTourism, statusFavorite)
                    setStatusFavorite(statusFavorite)
                }
            }
        }
        detailTourism?.let {

        }
    }

    private fun ImageView.loadImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_refresh))
            .error(R.drawable.ic_error_image)
            .into(this)
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_selected))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_unselected))
        }
    }


}