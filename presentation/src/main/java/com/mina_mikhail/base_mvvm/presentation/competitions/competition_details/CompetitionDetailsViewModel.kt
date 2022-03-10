package com.mina_mikhail.base_mvvm.presentation.competitions.competition_details

import androidx.lifecycle.viewModelScope
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Competition
import com.mina_mikhail.base_mvvm.domain.competitions.use_case.GetCompetitionDetailsUseCase
import com.mina_mikhail.base_mvvm.domain.utils.Resource
import com.mina_mikhail.base_mvvm.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CompetitionDetailsViewModel @Inject constructor(
  private val getCompetitionDetailsUseCase: GetCompetitionDetailsUseCase
) : BaseViewModel() {

  private val _competitionDetailsResponse = MutableStateFlow<Resource<Competition>>(Resource.Default)
  val competitionDetailsResponse = _competitionDetailsResponse

  fun getCompetitionDetails(competitionID: Int) {
    getCompetitionDetailsUseCase(competitionID)
      .onEach { result ->
        _competitionDetailsResponse.value = result
      }
      .launchIn(viewModelScope)
  }
}