package com.mina_mikhail.base_mvvm.domain.competitions.use_case

import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Competition
import com.mina_mikhail.base_mvvm.domain.competitions.repository.CompetitionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AddRemoveCompetitionToFavoritesUseCase @Inject constructor(
  private val competitionsRepository: CompetitionsRepository
) {

  operator fun invoke(competition: Competition): Flow<Boolean> = flow {
    if (competition.isFavourite) {
      competitionsRepository.deleteCompetitionFromLocal(competition)
      emit(false)
    } else {
      competitionsRepository.saveCompetitionToLocal(competition)
      emit(true)
    }
  }.flowOn(Dispatchers.IO)
}