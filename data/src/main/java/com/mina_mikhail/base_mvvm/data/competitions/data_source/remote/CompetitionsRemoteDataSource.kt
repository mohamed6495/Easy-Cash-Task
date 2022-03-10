package com.mina_mikhail.base_mvvm.data.competitions.data_source.remote

import com.mina_mikhail.base_mvvm.data.remote.BaseRemoteDataSource
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Competition
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.CompetitionDetails
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Team
import com.mina_mikhail.base_mvvm.domain.utils.FailureStatus
import com.mina_mikhail.base_mvvm.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CompetitionsRemoteDataSource @Inject constructor(private val apiService: CompetitionsServices) :
  BaseRemoteDataSource() {

  suspend fun getCompetitions() = safeApiCall {
    apiService.getCompetitions()
  }

  suspend fun getCompetitionDetails(competitionID: Int): Resource<CompetitionDetails> {
    return withContext(Dispatchers.IO) {
      try {
        val competitionDetailsResponseDeferred =
          async { safeApiCall { apiService.getCompetitionDetails(competitionID) } }
        val competitionTeamsResponseDeferred =
          async { safeApiCall { apiService.getCompetitionTeams(competitionID) } }

        val competitionDetailsResponse = competitionDetailsResponseDeferred.await()
        val competitionTeamsResponse = competitionTeamsResponseDeferred.await()

        if (competitionDetailsResponse is Resource.Success && competitionTeamsResponse is Resource.Success) {
          val competitionDetails: Competition = competitionDetailsResponse.value
          val competitionTeams: List<Team> = competitionTeamsResponse.value.teams

          Resource.Success(CompetitionDetails(competitionDetails, competitionTeams))
        } else {
          Resource.Failure(FailureStatus.API_FAIL, 200, null)
        }
      } catch (ex: Exception) {
        Resource.Failure(FailureStatus.API_FAIL, 200, null)
      }
    }
  }
}