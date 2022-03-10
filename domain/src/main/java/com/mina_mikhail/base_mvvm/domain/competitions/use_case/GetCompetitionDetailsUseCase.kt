package com.mina_mikhail.base_mvvm.domain.competitions.use_case

import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Competition
import com.mina_mikhail.base_mvvm.domain.competitions.repository.CompetitionsRepository
import com.mina_mikhail.base_mvvm.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCompetitionDetailsUseCase @Inject constructor(
  private val competitionsRepository: CompetitionsRepository
) {

  operator fun invoke(competitionID: Int): Flow<Resource<Competition>> = flow {
    emit(Resource.Loading)

    val result = competitionsRepository.getCompetitionDetails(competitionID)
    if (result is Resource.Success) {
      emit(Resource.Success(result.value))
    } else if (result is Resource.Failure) {
      emit(result)
    }
  }.flowOn(Dispatchers.IO)
}