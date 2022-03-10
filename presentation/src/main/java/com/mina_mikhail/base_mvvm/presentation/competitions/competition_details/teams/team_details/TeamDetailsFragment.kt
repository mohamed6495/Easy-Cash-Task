package com.mina_mikhail.base_mvvm.presentation.competitions.competition_details.teams.team_details

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Team
import com.mina_mikhail.base_mvvm.domain.enums.DataStatus
import com.mina_mikhail.base_mvvm.domain.utils.Resource
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.base.BaseFragment
import com.mina_mikhail.base_mvvm.presentation.base.extensions.backToPreviousScreen
import com.mina_mikhail.base_mvvm.presentation.base.extensions.getMyDrawable
import com.mina_mikhail.base_mvvm.presentation.base.extensions.getMyString
import com.mina_mikhail.base_mvvm.presentation.base.extensions.handleApiError
import com.mina_mikhail.base_mvvm.presentation.base.extensions.show
import com.mina_mikhail.base_mvvm.presentation.databinding.FragmentTeamDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class TeamDetailsFragment : BaseFragment<FragmentTeamDetailsBinding>() {

  private val viewModel: TeamDetailsViewModel by viewModels()

  private var teamID: Int = 0

  private lateinit var playersAdapter: PlayersAdapter

  override
  fun getLayoutId() = R.layout.fragment_team_details

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
    binding.contentGeneral.baseViewModel = viewModel
  }

  override
  fun getFragmentArguments() {
    val args: TeamDetailsFragmentArgs by navArgs()

    teamID = args.teamID
  }

  override
  fun setUpViews() {
    setUpToolbar()

    setUpPlayersRecyclerView()

    getTeamDetails()
  }

  private fun setUpToolbar() {
    binding.includedToolbar.toolbarTitle.text = getMyString(R.string.team_details)
    binding.includedToolbar.backIv.setOnClickListener { backToPreviousScreen() }

    binding.includedToolbar.ivAction.show()
    binding.includedToolbar.ivAction.setImageDrawable(getMyDrawable(R.drawable.ic_favorites))
    binding.includedToolbar.ivAction.setOnClickListener { viewModel.addRemoveTeamToFavorites() }
  }

  private fun setUpPlayersRecyclerView() {
    playersAdapter = PlayersAdapter()
    binding.recyclerView.apply {
      setHasFixedSize(true)
      layoutManager = LinearLayoutManager(requireContext())
      adapter = playersAdapter
    }
  }

  private fun getTeamDetails() {
    viewModel.getTeamDetails(teamID)
  }

  override
  fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.teamDetailsResponse.collect {
        when (it) {
          Resource.Loading -> {
            viewModel.dataLoadingEvent.value = DataStatus.LOADING
          }
          is Resource.Success -> {
            setTeamData(it.value)
            viewModel.dataLoadingEvent.value = DataStatus.SHOW_DATA
          }
          is Resource.Failure -> {
            handleApiError(
              it,
              noDataAction = { viewModel.dataLoadingEvent.value = DataStatus.NO_DATA },
              noInternetAction = { viewModel.dataLoadingEvent.value = DataStatus.NO_INTERNET }
            )
          }
        }
      }
    }

    lifecycleScope.launchWhenResumed {
      viewModel.addRemoveTeamToFavorites.collect {
        it?.let { isFavourite ->
          viewModel.team.isFavourite = isFavourite
          if (isFavourite) {
            binding.includedToolbar.ivAction.setImageDrawable(getMyDrawable(R.drawable.ic_favorites_selected))
          } else {
            binding.includedToolbar.ivAction.setImageDrawable(getMyDrawable(R.drawable.ic_favorites))
          }
        }
      }
    }
  }

  private fun setTeamData(team: Team) {
    binding.team = team
    viewModel.team = team

    playersAdapter.submitList(team.squad)
  }
}