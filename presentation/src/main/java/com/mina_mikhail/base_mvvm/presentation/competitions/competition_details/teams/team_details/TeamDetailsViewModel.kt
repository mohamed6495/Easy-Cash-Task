package com.mina_mikhail.base_mvvm.presentation.competitions.competition_details.teams.team_details

import androidx.lifecycle.viewModelScope
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Team
import com.mina_mikhail.base_mvvm.domain.competitions.use_case.AddRemoveTeamToFavoritesUseCase
import com.mina_mikhail.base_mvvm.domain.competitions.use_case.GetTeamDetailsUseCase
import com.mina_mikhail.base_mvvm.domain.competitions.use_case.GetTeamFromLocalUseCase
import com.mina_mikhail.base_mvvm.domain.utils.Resource
import com.mina_mikhail.base_mvvm.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TeamDetailsViewModel @Inject constructor(
  private val getTeamDetailsUseCase: GetTeamDetailsUseCase,
  private val addRemoveTeamToFavoritesUseCase: AddRemoveTeamToFavoritesUseCase,
  private val getTeamFromLocalUseCase: GetTeamFromLocalUseCase
) : BaseViewModel() {

  lateinit var team: Team

  private val _teamDetailsResponse = MutableStateFlow<Resource<Team>>(Resource.Default)
  val teamDetailsResponse = _teamDetailsResponse

  private val _localTeam = MutableStateFlow<Team?>(null)
  val localTeam = _localTeam

  private val _addRemoveTeamToFavorites = MutableStateFlow<Boolean?>(null)
  val addRemoveTeamToFavorites = _addRemoveTeamToFavorites

  fun getTeamDetails(teamID: Int) {
    getTeamDetailsUseCase(teamID)
      .onEach { result ->
        _teamDetailsResponse.value = result
      }
      .launchIn(viewModelScope)
  }

  fun getTeamFromLocal() {
    getTeamFromLocalUseCase(team.id)
      .onEach { result ->
        _localTeam.value = result
      }
      .launchIn(viewModelScope)
  }

  fun addRemoveTeamToFavorites() {
    addRemoveTeamToFavoritesUseCase(team)
      .onEach { result ->
        _addRemoveTeamToFavorites.value = result
      }
      .launchIn(viewModelScope)
  }
}