package com.mina_mikhail.base_mvvm.presentation.competitions.competition_details.teams.team_details

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Team
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.base.BaseFragment
import com.mina_mikhail.base_mvvm.presentation.base.extensions.backToPreviousScreen
import com.mina_mikhail.base_mvvm.presentation.base.extensions.getMyString
import com.mina_mikhail.base_mvvm.presentation.base.extensions.show
import com.mina_mikhail.base_mvvm.presentation.base.extensions.toJsonModel
import com.mina_mikhail.base_mvvm.presentation.databinding.FragmentTeamDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamDetailsFragment : BaseFragment<FragmentTeamDetailsBinding>() {

  private val viewModel: TeamDetailsViewModel by viewModels()

  private lateinit var team: Team

  override
  fun getLayoutId() = R.layout.fragment_team_details

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

  override
  fun getFragmentArguments() {
    val args: TeamDetailsFragmentArgs by navArgs()

    team = args.team.toJsonModel(Team::class.java)
  }

  override
  fun setUpViews() {
    setUpToolbar()

    setTeamData()
  }

  private fun setUpToolbar() {
    binding.includedToolbar.toolbarTitle.text = getMyString(R.string.team_details)
    binding.includedToolbar.backIv.setOnClickListener { backToPreviousScreen() }

    binding.includedToolbar.ivAction.show()
    binding.includedToolbar.ivAction.setOnClickListener { viewModel.addRemoveTeamToFavorites() }
  }

  private fun setTeamData() {
    binding.team = team
  }
}