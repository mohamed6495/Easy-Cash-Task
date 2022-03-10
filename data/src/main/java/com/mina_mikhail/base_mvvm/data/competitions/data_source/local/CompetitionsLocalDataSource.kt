package com.mina_mikhail.base_mvvm.data.competitions.data_source.local

import com.mina_mikhail.base_mvvm.data.competitions.entity.CompetitionEntity
import com.mina_mikhail.base_mvvm.data.competitions.entity.TeamEntity
import com.mina_mikhail.base_mvvm.data.local.dp.MyDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CompetitionsLocalDataSource @Inject constructor(private val myDatabase: MyDatabase) {

  fun getTeamsFromLocal(): List<TeamEntity> =
    myDatabase.getTeamsDao().getAll()

  fun getTeamById(teamID: Int): TeamEntity =
    myDatabase.getTeamsDao().getTeamById(teamID)

  suspend fun saveTeamToLocal(team: TeamEntity) =
    myDatabase.getTeamsDao().insert(team)

  suspend fun deleteTeamFromLocal(team: TeamEntity) =
    myDatabase.getTeamsDao().delete(team)

  fun getCompetitionsFromLocal(): Flow<List<CompetitionEntity>> =
    myDatabase.getCompetitionsDao().getAll()

  fun getCompetitionById(competitionID: Int): CompetitionEntity =
    myDatabase.getCompetitionsDao().getCompetitionById(competitionID)

  suspend fun saveCompetitionToLocal(competition: CompetitionEntity) =
    myDatabase.getCompetitionsDao().insert(competition)

  suspend fun deleteCompetitionFromLocal(competition: CompetitionEntity) =
    myDatabase.getCompetitionsDao().delete(competition)
}