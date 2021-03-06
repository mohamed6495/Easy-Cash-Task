package com.mina_mikhail.base_mvvm.domain.auth.use_case

import com.mina_mikhail.base_mvvm.domain.auth.entity.request.LogInRequest
import com.mina_mikhail.base_mvvm.domain.auth.entity.request.LogInValidationException
import com.mina_mikhail.base_mvvm.domain.auth.enums.AuthFieldsValidation
import com.mina_mikhail.base_mvvm.domain.auth.repository.AuthRepository
import com.mina_mikhail.base_mvvm.domain.utils.BaseResponse
import com.mina_mikhail.base_mvvm.domain.utils.Resource
import com.mina_mikhail.base_mvvm.domain.utils.isValidEmail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LogInUseCase @Inject constructor(
  private val authRepository: AuthRepository
) {

  @Throws(LogInValidationException::class)
  operator fun invoke(request: LogInRequest): Flow<Resource<BaseResponse>> = flow {
    if (request.email.isEmpty()) {
      throw LogInValidationException(AuthFieldsValidation.EMPTY_EMAIL.value.toString())
    }

    if (!request.email.isValidEmail()) {
      throw LogInValidationException(AuthFieldsValidation.INVALID_EMAIL.value.toString())
    }

    if (request.password.isEmpty()) {
      throw LogInValidationException(AuthFieldsValidation.EMPTY_PASSWORD.value.toString())
    }

    emit(Resource.Loading)

    //  emit(result)
  }.flowOn(Dispatchers.IO)
}