package com.mina_mikhail.base_mvvm.domain.competitions.repository

import com.mina_mikhail.base_mvvm.domain.competitions.entity.response.CompetitionsResponse
import com.mina_mikhail.base_mvvm.domain.utils.Resource

interface CompetitionsRepository {

  suspend fun getCompetitions(): Resource<CompetitionsResponse>
}