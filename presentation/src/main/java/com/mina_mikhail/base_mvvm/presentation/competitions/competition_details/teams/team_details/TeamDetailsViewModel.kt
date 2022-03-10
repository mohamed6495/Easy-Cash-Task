package com.mina_mikhail.base_mvvm.presentation.competitions.competition_details.teams.team_details

import com.mina_mikhail.base_mvvm.domain.competitions.use_case.GetCompetitionDetailsUseCase
import com.mina_mikhail.base_mvvm.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TeamDetailsViewModel @Inject constructor(
  private val getCompetitionDetailsUseCase: GetCompetitionDetailsUseCase
) : BaseViewModel() {

  fun addRemoveTeamToFavorites() {
  }
}