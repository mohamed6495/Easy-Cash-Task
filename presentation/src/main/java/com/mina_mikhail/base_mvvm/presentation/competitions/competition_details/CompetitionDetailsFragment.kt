package com.mina_mikhail.base_mvvm.presentation.competitions.competition_details

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.mina_mikhail.base_mvvm.domain.enums.DataStatus
import com.mina_mikhail.base_mvvm.domain.utils.Resource
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.base.BaseFragment
import com.mina_mikhail.base_mvvm.presentation.base.extensions.backToPreviousScreen
import com.mina_mikhail.base_mvvm.presentation.base.extensions.getMyString
import com.mina_mikhail.base_mvvm.presentation.base.extensions.handleApiError
import com.mina_mikhail.base_mvvm.presentation.databinding.FragmentCompetitionDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CompetitionDetailsFragment : BaseFragment<FragmentCompetitionDetailsBinding>() {

  private val viewModel: CompetitionDetailsViewModel by viewModels()

  private var competitionID: Int = 0

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
            //    competitionsAdapter.submitList(it.value)

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
  }
}