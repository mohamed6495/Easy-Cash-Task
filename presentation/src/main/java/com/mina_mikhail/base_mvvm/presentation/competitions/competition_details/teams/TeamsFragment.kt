package com.mina_mikhail.base_mvvm.presentation.competitions.competition_details.teams

import androidx.recyclerview.widget.LinearLayoutManager
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Team
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.base.BaseFragment
import com.mina_mikhail.base_mvvm.presentation.base.extensions.navigateSafe
import com.mina_mikhail.base_mvvm.presentation.base.extensions.toJsonList
import com.mina_mikhail.base_mvvm.presentation.competitions.competition_details.CompetitionDetailsFragmentDirections
import com.mina_mikhail.base_mvvm.presentation.databinding.FragmentTeamsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamsFragment : BaseFragment<FragmentTeamsBinding>() {

  companion object {
    const val TEAMS = "TEAMS"
  }

  private lateinit var teamsAdapter: TeamsAdapter
  private lateinit var teams: List<Team>

  override
  fun getLayoutId() = R.layout.fragment_teams

  override
  fun getFragmentArguments() {
    arguments?.let {
      it.getString(TEAMS)?.let { teamsStr ->
        teams = teamsStr.toJsonList<List<Team>>()
      }
    }
  }

  override
  fun setUpViews() {
    setUpTeamsRecyclerView()
  }

  private fun setUpTeamsRecyclerView() {
    teamsAdapter = TeamsAdapter { onTeamClicked(it) }.apply { submitList(teams) }
    binding.recyclerView.apply {
      setHasFixedSize(true)
      layoutManager = LinearLayoutManager(requireContext())
      adapter = teamsAdapter
    }
  }

  private fun onTeamClicked(team: Team) {
    navigateSafe(CompetitionDetailsFragmentDirections.actionOpenTeamDetails(team.id))
  }
}