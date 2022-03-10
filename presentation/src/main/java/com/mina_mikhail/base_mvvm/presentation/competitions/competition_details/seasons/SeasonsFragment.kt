package com.mina_mikhail.base_mvvm.presentation.competitions.competition_details.seasons

import androidx.recyclerview.widget.LinearLayoutManager
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Season
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.base.BaseFragment
import com.mina_mikhail.base_mvvm.presentation.base.extensions.toJsonList
import com.mina_mikhail.base_mvvm.presentation.databinding.FragmentSeasonsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeasonsFragment : BaseFragment<FragmentSeasonsBinding>() {

  companion object {
    const val SEASONS = "SEASONS"
  }

  private lateinit var seasonsAdapter: SeasonsAdapter
  private lateinit var seasons: List<Season>

  override
  fun getLayoutId() = R.layout.fragment_seasons

  override
  fun getFragmentArguments() {
    arguments?.let {
      it.getString(SEASONS)?.let { seasonsStr ->
        seasons = seasonsStr.toJsonList<List<Season>>()
      }
    }
  }

  override
  fun setUpViews() {
    setUpSeasonsRecyclerView()
  }

  private fun setUpSeasonsRecyclerView() {
    seasonsAdapter = SeasonsAdapter().apply { submitList(seasons) }
    binding.recyclerView.apply {
      setHasFixedSize(true)
      layoutManager = LinearLayoutManager(requireContext())
      adapter = seasonsAdapter
    }
  }
}