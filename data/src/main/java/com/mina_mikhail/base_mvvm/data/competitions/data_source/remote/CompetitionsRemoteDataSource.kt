package com.mina_mikhail.base_mvvm.data.competitions.data_source.remote

import com.mina_mikhail.base_mvvm.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class CompetitionsRemoteDataSource @Inject constructor(private val apiService: CompetitionsServices) :
  BaseRemoteDataSource() {

  suspend fun getCompetitions() = safeApiCall {
    apiService.getCompetitions()
  }
}