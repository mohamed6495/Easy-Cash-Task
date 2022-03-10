package com.mina_mikhail.base_mvvm.data.auth.repository

import com.mina_mikhail.base_mvvm.data.auth.data_source.remote.AuthRemoteDataSource
import com.mina_mikhail.base_mvvm.domain.auth.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
  private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository