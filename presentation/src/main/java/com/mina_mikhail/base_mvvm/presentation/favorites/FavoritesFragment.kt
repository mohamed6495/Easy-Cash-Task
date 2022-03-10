package com.mina_mikhail.base_mvvm.presentation.favorites

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Team
import com.mina_mikhail.base_mvvm.domain.enums.DataStatus
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.base.BaseFragment
import com.mina_mikhail.base_mvvm.presentation.base.extensions.navigateSafe
import com.mina_mikhail.base_mvvm.presentation.competitions.competition_details.teams.TeamsAdapter
import com.mina_mikhail.base_mvvm.presentation.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>() {

  private val viewModel: FavoritesViewModel by viewModels()

  private lateinit var teamsAdapter: TeamsAdapter

  override
  fun getLayoutId() = R.layout.fragment_favorites

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
    binding.listGeneral.baseViewModel = viewModel
  }

  override
  fun setUpViews() {
    setUpTeamsRecyclerView()
  }

  override
  fun onResume() {
    super.onResume()

    getFavourites()
  }

  private fun setUpTeamsRecyclerView() {
    teamsAdapter = TeamsAdapter { onTeamClicked(it) }
    binding.listGeneral.recyclerView.apply {
      setHasFixedSize(true)
      layoutManager = LinearLayoutManager(requireContext())
      adapter = teamsAdapter
    }
  }

  private fun onTeamClicked(team: Team) {
    navigateSafe(FavoritesFragmentDirections.actionOpenTeamDetails(team.id))
  }

  private fun getFavourites() {
    viewModel.getTeamsFromLocal()
  }

  override
  fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.localTeams.collect {
        if (it.isEmpty()) {
          viewModel.dataLoadingEvent.value = DataStatus.NO_DATA
        } else {
          teamsAdapter.submitList(it)
          viewModel.dataLoadingEvent.value = DataStatus.SHOW_DATA
        }
      }
    }
  }
}