package com.mina_mikhail.base_mvvm.presentation.competitions

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Competition
import com.mina_mikhail.base_mvvm.domain.enums.DataStatus
import com.mina_mikhail.base_mvvm.domain.utils.Resource
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.base.BaseFragment
import com.mina_mikhail.base_mvvm.presentation.base.extensions.handleApiError
import com.mina_mikhail.base_mvvm.presentation.base.extensions.navigateSafe
import com.mina_mikhail.base_mvvm.presentation.databinding.FragmentCompetitionsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CompetitionsFragment : BaseFragment<FragmentCompetitionsBinding>() {

  private val viewModel: CompetitionsViewModel by viewModels()

  private lateinit var competitionsAdapter: CompetitionsAdapter

  override
  fun getLayoutId() = R.layout.fragment_competitions

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
    binding.listGeneral.baseViewModel = viewModel
  }

  override
  fun setUpViews() {
    setUpCompetitionsRecyclerView()

    getCompetitions()
  }

  private fun setUpCompetitionsRecyclerView() {
    competitionsAdapter = CompetitionsAdapter { onCompetitionClicked(it) }
    binding.listGeneral.recyclerView.apply {
      setHasFixedSize(true)
      layoutManager = LinearLayoutManager(requireContext())
      adapter = competitionsAdapter
    }
  }

  private fun onCompetitionClicked(competition: Competition) {
    navigateSafe(CompetitionsFragmentDirections.actionOpenCompetitionDetails(competition.id))
  }

  private fun getCompetitions() {
    viewModel.getCompetitions()
  }

  override
  fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.competitionsResponse.collect {
        when (it) {
          Resource.Loading -> {
            viewModel.dataLoadingEvent.value = DataStatus.LOADING
          }
          is Resource.Success -> {
            competitionsAdapter.submitList(it.value)
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