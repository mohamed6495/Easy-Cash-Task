package com.mina_mikhail.base_mvvm.presentation.auth.log_in

import com.mina_mikhail.base_mvvm.domain.auth.use_case.LogInUseCase
import com.mina_mikhail.base_mvvm.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
  private val logInUseCase: LogInUseCase
) : BaseViewModel() {

  // private val _logInResponse = MutableStateFlow<Resource<BaseResponse<User>>>(Resource.Default)
  // val logInResponse = _logInResponse

  fun onLogInClicked() {
//    logInUseCase(request)
//      .catch { exception -> validationException.value = exception.message?.toInt() }
//      .onEach { result ->
//        _logInResponse.value = result
//      }
//      .launchIn(viewModelScope)
  }
}