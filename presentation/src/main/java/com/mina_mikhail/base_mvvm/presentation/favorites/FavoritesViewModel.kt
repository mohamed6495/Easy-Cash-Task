package com.mina_mikhail.base_mvvm.presentation.favorites

import androidx.lifecycle.viewModelScope
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Team
import com.mina_mikhail.base_mvvm.domain.favorites.use_case.GetTeamsFromLocalUseCase
import com.mina_mikhail.base_mvvm.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
  private val getTeamsFromLocalUseCase: GetTeamsFromLocalUseCase
) : BaseViewModel() {

  private val _localTeams = MutableStateFlow<List<Team>>(emptyList())
  val localTeams = _localTeams

  fun getTeamsFromLocal() {
    getTeamsFromLocalUseCase()
      .onEach { result ->
        _localTeams.value = result
      }
      .launchIn(viewModelScope)
  }
}