package com.mina_mikhail.base_mvvm.domain.competitions.repository

import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.CompetitionDetails
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Team
import com.mina_mikhail.base_mvvm.domain.competitions.entity.response.CompetitionsResponse
import com.mina_mikhail.base_mvvm.domain.utils.Resource

interface CompetitionsRepository {

  suspend fun getCompetitions(): Resource<CompetitionsResponse>

  suspend fun getCompetitionDetails(competitionID: Int): Resource<CompetitionDetails>

  suspend fun getTeamDetails(teamID: Int): Resource<Team>
}