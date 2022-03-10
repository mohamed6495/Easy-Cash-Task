package com.mina_mikhail.base_mvvm.data.competitions.repository

import com.mina_mikhail.base_mvvm.data.competitions.data_source.local.CompetitionsLocalDataSource
import com.mina_mikhail.base_mvvm.data.competitions.data_source.remote.CompetitionsRemoteDataSource
import com.mina_mikhail.base_mvvm.data.competitions.mapper.CompetitionsMapper.mapToEntity
import com.mina_mikhail.base_mvvm.data.competitions.mapper.CompetitionsMapper.mapToUiModel
import com.mina_mikhail.base_mvvm.data.competitions.mapper.CompetitionsMapper.mapToUiModelList
import com.mina_mikhail.base_mvvm.data.competitions.mapper.TeamMapper.mapToEntity
import com.mina_mikhail.base_mvvm.data.competitions.mapper.TeamMapper.mapToUiMode
import com.mina_mikhail.base_mvvm.data.competitions.mapper.TeamMapper.mapToUiModelList
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Competition
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Team
import com.mina_mikhail.base_mvvm.domain.competitions.repository.CompetitionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CompetitionsRepositoryImpl @Inject constructor(
  private val remoteDataSource: CompetitionsRemoteDataSource,
  private val localDataSource: CompetitionsLocalDataSource
) : CompetitionsRepository {

  override
  suspend fun getCompetitions() = remoteDataSource.getCompetitions()

  override
  suspend fun getCompetitionDetails(competitionID: Int) = remoteDataSource.getCompetitionDetails(competitionID)

  override
  suspend fun getTeamDetails(teamID: Int) = remoteDataSource.getTeamDetails(teamID)

  override
  suspend fun getTeamsFromLocal(): Flow<List<Team>> {
    return localDataSource.getTeamsFromLocal().map { it.mapToUiModelList() }
  }

  override
  suspend fun getTeamById(teamID: Int): Flow<Team?> {
    return localDataSource.getTeamById(teamID).map { it.mapToUiMode() }
  }

  override
  suspend fun saveTeamToLocal(team: Team) = localDataSource.saveTeamToLocal(team.mapToEntity())

  override
  suspend fun deleteTeamFromLocal(team: Team) = localDataSource.deleteTeamFromLocal(team.mapToEntity())

  override
  suspend fun getCompetitionsFromLocal(): Flow<List<Competition>> {
    return localDataSource.getCompetitionsFromLocal().map { it.mapToUiModelList() }
  }

  override
  suspend fun getCompetitionById(competitionID: Int): Flow<Competition> {
    return localDataSource.getCompetitionById(competitionID).map { it.mapToUiModel() }
  }

  override
  suspend fun saveCompetitionToLocal(competition: Competition) =
    localDataSource.saveCompetitionToLocal(competition.mapToEntity())

  override
  suspend fun deleteCompetitionFromLocal(competition: Competition) =
    localDataSource.deleteCompetitionFromLocal(competition.mapToEntity())
}