package com.mina_mikhail.base_mvvm.domain.competitions.repository

import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Competition
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.CompetitionDetails
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Team
import com.mina_mikhail.base_mvvm.domain.competitions.entity.response.CompetitionsResponse
import com.mina_mikhail.base_mvvm.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CompetitionsRepository {

  suspend fun getCompetitions(): Resource<CompetitionsResponse>

  suspend fun getCompetitionDetails(competitionID: Int): Resource<CompetitionDetails>

  suspend fun getTeamDetails(teamID: Int): Resource<Team>

  suspend fun getTeamsFromLocal(): List<Team>

  suspend fun getTeamById(teamID: Int): Team?

  suspend fun saveTeamToLocal(team: Team)

  suspend fun deleteTeamFromLocal(team: Team)

  suspend fun getCompetitionsFromLocal(): Flow<List<Competition>>

  suspend fun getCompetitionById(competitionID: Int): Competition?

  suspend fun saveCompetitionToLocal(competition: Competition)

  suspend fun deleteCompetitionFromLocal(competition: Competition)
}