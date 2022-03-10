package com.mina_mikhail.base_mvvm.presentation.competitions

import androidx.lifecycle.viewModelScope
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Competition
import com.mina_mikhail.base_mvvm.domain.competitions.use_case.GetCompetitionsUseCase
import com.mina_mikhail.base_mvvm.domain.utils.Resource
import com.mina_mikhail.base_mvvm.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CompetitionsViewModel @Inject constructor(
  private val getCompetitionsUseCase: GetCompetitionsUseCase
) : BaseViewModel() {

  private val _competitionsResponse = MutableStateFlow<Resource<List<Competition>>>(Resource.Default)
  val competitionsResponse = _competitionsResponse

  fun getCompetitions() {
    getCompetitionsUseCase()
      .onEach { result ->
        _competitionsResponse.value = result
      }
      .launchIn(viewModelScope)
  }
}