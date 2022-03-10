package com.mina_mikhail.base_mvvm.data.competitions.repository

import com.mina_mikhail.base_mvvm.data.competitions.data_source.remote.CompetitionsRemoteDataSource
import com.mina_mikhail.base_mvvm.domain.competitions.repository.CompetitionsRepository
import javax.inject.Inject

class CompetitionsRepositoryImpl @Inject constructor(private val remoteDataSource: CompetitionsRemoteDataSource) :
  CompetitionsRepository {

  override
  suspend fun getCompetitions() = remoteDataSource.getCompetitions()

  override
  suspend fun getCompetitionDetails(competitionID: Int) = remoteDataSource.getCompetitionDetails(competitionID)

  override
  suspend fun getTeamDetails(teamID: Int) = remoteDataSource.getTeamDetails(teamID)
}