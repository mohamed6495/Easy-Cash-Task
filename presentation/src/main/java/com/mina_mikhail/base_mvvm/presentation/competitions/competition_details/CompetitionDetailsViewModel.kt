package com.mina_mikhail.base_mvvm.presentation.competitions.competition_details

import androidx.lifecycle.viewModelScope
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Competition
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.CompetitionDetails
import com.mina_mikhail.base_mvvm.domain.competitions.use_case.AddRemoveCompetitionToFavoritesUseCase
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
  private val getCompetitionDetailsUseCase: GetCompetitionDetailsUseCase,
  private val addRemoveCompetitionToFavoritesUseCase: AddRemoveCompetitionToFavoritesUseCase
) : BaseViewModel() {

  lateinit var competition: Competition

  private val _competitionDetailsResponse = MutableStateFlow<Resource<CompetitionDetails>>(Resource.Default)
  val competitionDetailsResponse = _competitionDetailsResponse

  private val _addRemoveCompetitionToFavorites = MutableStateFlow<Boolean?>(null)
  val addRemoveCompetitionToFavorites = _addRemoveCompetitionToFavorites

  fun getCompetitionDetails(competitionID: Int) {
    getCompetitionDetailsUseCase(competitionID)
      .onEach { result ->
        _competitionDetailsResponse.value = result
      }
      .launchIn(viewModelScope)
  }

  fun addRemoveCompetitionToFavorites() {
    addRemoveCompetitionToFavoritesUseCase(competition)
      .onEach { result ->
        _addRemoveCompetitionToFavorites.value = result
      }
      .launchIn(viewModelScope)
  }
}