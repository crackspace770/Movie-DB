package com.fajar.moviedb.ui.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fajar.moviedb.R
import com.fajar.moviedb.core.data.source.Resource
import com.fajar.moviedb.core.ui.TvAdapter
import com.fajar.moviedb.databinding.FragmentTvBinding
import com.fajar.moviedb.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvFragment : Fragment() {

    private val viewModel: TvViewModel by viewModels()
    private var _binding: FragmentTvBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val tvAdapter = TvAdapter()
            tvAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            viewModel.tv.observe(viewLifecycleOwner) { movie ->
                binding.apply {
                    if (movie != null) {
                        when (movie) {
                            is Resource.Loading -> progressBar.visibility = View.VISIBLE
                            is Resource.Success -> {
                                progressBar.visibility = View.GONE
                                swipeToRefresh.isRefreshing = false
                                tvAdapter.setData(movie.data)
                            }
                            is Resource.Error -> {
                                progressBar.visibility = View.GONE
                                viewError.root.visibility = View.VISIBLE
                                viewError.tvError.text =
                                    movie.message ?: getString(R.string.something_wrong)
                            }
                        }
                    }
                }

            }

            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }
            binding.apply {

            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}