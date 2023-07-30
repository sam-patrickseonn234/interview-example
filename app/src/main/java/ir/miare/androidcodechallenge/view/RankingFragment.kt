package ir.miare.androidcodechallenge.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.miare.androidcodechallenge.R
import ir.miare.androidcodechallenge.databinding.FragmentRankingBinding
import ir.miare.androidcodechallenge.utiles.ResultWrapper
import ir.miare.androidcodechallenge.view.adapter.LeagueAdapter
import ir.miare.androidcodechallenge.view.viewModel.PlayersViewModel

@AndroidEntryPoint
class RankingFragment(val sortingMode: Int) : Fragment() {
    lateinit var leagueAdapter: LeagueAdapter

    var binding: FragmentRankingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ranking, container, false)
        binding = FragmentRankingBinding.bind(view)
        val viewModel: PlayersViewModel by viewModels()
        setupLeagueAdapter(binding!!)
        viewModel.allPlayers.observe(
            viewLifecycleOwner,
            Observer { response ->
                when (response) {
                    is ResultWrapper.Error -> {
                        response.exception.printStackTrace()
                    }
                    ResultWrapper.Loading -> {
                        binding!!.pbLoading.show()
                    }
                    is ResultWrapper.Success -> {
                        response.data?.let { data ->
                            if (sortingMode == -1) {
                                binding!!.pbLoading.hide()
                                leagueAdapter.differ.submitList(data)
                            }
                        }
                    }
                }
            },

        )

        return view
    }
    private fun setupLeagueAdapter(binding: FragmentRankingBinding) {
        leagueAdapter = LeagueAdapter(requireActivity())
        binding.mainRecyclerView.apply {
            adapter = leagueAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }
}
