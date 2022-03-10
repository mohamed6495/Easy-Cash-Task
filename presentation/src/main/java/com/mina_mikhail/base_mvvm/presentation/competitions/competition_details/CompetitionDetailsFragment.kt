package com.mina_mikhail.base_mvvm.presentation.competitions.competition_details

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.CompetitionDetails
import com.mina_mikhail.base_mvvm.domain.enums.DataStatus
import com.mina_mikhail.base_mvvm.domain.utils.Resource
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.base.BaseFragment
import com.mina_mikhail.base_mvvm.presentation.base.MyViewPagerAdapter
import com.mina_mikhail.base_mvvm.presentation.base.extensions.backToPreviousScreen
import com.mina_mikhail.base_mvvm.presentation.base.extensions.getMyDrawable
import com.mina_mikhail.base_mvvm.presentation.base.extensions.getMyString
import com.mina_mikhail.base_mvvm.presentation.base.extensions.handleApiError
import com.mina_mikhail.base_mvvm.presentation.base.extensions.newFragmentInstance
import com.mina_mikhail.base_mvvm.presentation.base.extensions.show
import com.mina_mikhail.base_mvvm.presentation.base.extensions.toJsonString
import com.mina_mikhail.base_mvvm.presentation.competitions.competition_details.seasons.SeasonsFragment
import com.mina_mikhail.base_mvvm.presentation.competitions.competition_details.teams.TeamsFragment
import com.mina_mikhail.base_mvvm.presentation.databinding.FragmentCompetitionDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CompetitionDetailsFragment : BaseFragment<FragmentCompetitionDetailsBinding>() {

  private val viewModel: CompetitionDetailsViewModel by viewModels()

  private var competitionID: Int = 0

  private lateinit var seasonsFragment: SeasonsFragment
  private lateinit var teamsFragment: TeamsFragment

  override
  fun getLayoutId() = R.layout.fragment_competition_details

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
    binding.contentGeneral.baseViewModel = viewModel
  }

  override
  fun getFragmentArguments() {
    val args: CompetitionDetailsFragmentArgs by navArgs()

    competitionID = args.competitionID
  }

  override
  fun setUpViews() {
    setUpToolbar()

    getCompetitionDetails()
  }

  private fun setUpToolbar() {
    binding.includedToolbar.toolbarTitle.text = getMyString(R.string.competition_details)
    binding.includedToolbar.backIv.setOnClickListener { backToPreviousScreen() }

    binding.includedToolbar.ivAction.show()
    binding.includedToolbar.ivAction.setImageDrawable(getMyDrawable(R.drawable.ic_favorites))
    binding.includedToolbar.ivAction.setOnClickListener { viewModel.addRemoveCompetitionToFavorites() }
  }

  private fun getCompetitionDetails() {
    viewModel.getCompetitionDetails(competitionID)
  }

  override
  fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.competitionDetailsResponse.collect {
        when (it) {
          Resource.Loading -> {
            viewModel.dataLoadingEvent.value = DataStatus.LOADING
          }
          is Resource.Success -> {
            setCompetitionDetails(it.value)
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
      viewModel.addRemoveCompetitionToFavorites.collect {
        it?.let { isFavourite ->
          viewModel.competition.isFavourite = isFavourite
          if (isFavourite) {
            binding.includedToolbar.ivAction.setImageDrawable(getMyDrawable(R.drawable.ic_favorites_selected))
          } else {
            binding.includedToolbar.ivAction.setImageDrawable(getMyDrawable(R.drawable.ic_favorites))
          }
        }
      }
    }
  }

  private fun setCompetitionDetails(competitionDetails: CompetitionDetails) {
    binding.competition = competitionDetails.competition
    viewModel.competition = competitionDetails.competition

    setUpFragments(competitionDetails)

    setUpViewPager()

    setUpTabLayout()
  }

  private fun setUpFragments(competitionDetails: CompetitionDetails) {
    seasonsFragment = newFragmentInstance(
      SeasonsFragment.SEASONS
        to competitionDetails.competition.seasons.toJsonString()
    )

    teamsFragment = newFragmentInstance(
      TeamsFragment.TEAMS
        to competitionDetails.teams.toJsonString()
    )
  }

  private fun setUpViewPager() {
    val adapter = MyViewPagerAdapter(this, arrayListOf(seasonsFragment, teamsFragment))
    binding.viewPager.adapter = adapter
    binding.viewPager.isSaveEnabled = false
  }

  private fun setUpTabLayout() {
    binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL

    TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
      tab.text = if (position == 0) {
        getMyString(R.string.seasons)
      } else {
        getMyString(R.string.teams)
      }
    }.attach()
  }
}