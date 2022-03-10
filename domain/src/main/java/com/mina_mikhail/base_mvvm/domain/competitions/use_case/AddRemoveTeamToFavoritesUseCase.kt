package com.mina_mikhail.base_mvvm.domain.competitions.use_case

import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Team
import com.mina_mikhail.base_mvvm.domain.competitions.repository.CompetitionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AddRemoveTeamToFavoritesUseCase @Inject constructor(
  private val competitionsRepository: CompetitionsRepository
) {

  operator fun invoke(team: Team): Flow<Boolean> = flow {
    if (team.isFavourite) {
      competitionsRepository.deleteTeamFromLocal(team)
      emit(false)
    } else {
      competitionsRepository.saveTeamToLocal(team)
      emit(true)
    }
  }.flowOn(Dispatchers.IO)
}