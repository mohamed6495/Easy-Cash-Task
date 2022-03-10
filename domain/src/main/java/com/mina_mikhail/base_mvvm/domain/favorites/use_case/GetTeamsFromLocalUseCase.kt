package com.mina_mikhail.base_mvvm.domain.favorites.use_case

import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Team
import com.mina_mikhail.base_mvvm.domain.competitions.repository.CompetitionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetTeamsFromLocalUseCase @Inject constructor(
  private val competitionsRepository: CompetitionsRepository
) {

  operator fun invoke(): Flow<List<Team>> = flow {
    emit(competitionsRepository.getTeamsFromLocal())
  }.flowOn(Dispatchers.IO)
}