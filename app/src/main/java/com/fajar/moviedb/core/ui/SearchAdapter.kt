package com.fajar.moviedb.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fajar.moviedb.core.domain.model.Movie
import com.fajar.moviedb.core.utils.Constant
import com.fajar.moviedb.core.utils.SearchDiffUtil
import com.fajar.moviedb.databinding.ItemListTourismBinding

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.ViewHolder>()  {

    private lateinit var onItemClickCallback: ItemClickCallBack
    var listMovie = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemListTourismBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listMovie[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listMovie.size


    fun setData(items: List<Movie>) {
        val diffUtils = SearchDiffUtil(listMovie, items)
        val diffResult = DiffUtil.calculateDiff(diffUtils)
        listMovie.clear()
        listMovie.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val binding: ItemListTourismBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
                binding.apply {
                    Glide.with(itemView.context)
                        .load("${Constant.IMAGE_BASE_URL}${data.posterPath}")
                        .centerCrop()
                        .into(ivItemImage)
                    tvItemTitle.text=data.title
                    tvItemSubtitle.text = data.voteAverage.toString()

                    itemView.setOnClickListener {
                        onItemClickCallback.onItemClicked(data)
                    }
                }


        }
    }
    fun clearList(){
        val size = listMovie.size
        listMovie.clear()
        notifyItemRangeRemoved(0, size)
    }
}