package com.mina_mikhail.base_mvvm.domain.competitions.use_case

import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Competition
import com.mina_mikhail.base_mvvm.domain.competitions.repository.CompetitionsRepository
import com.mina_mikhail.base_mvvm.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCompetitionsUseCase @Inject constructor(
  private val competitionsRepository: CompetitionsRepository
) {

  private val supportedCompetitions =
    arrayListOf("WC", "CL", "BL1", "DED", "BSA", "PD", "FL1", "ELC", "PPL", "EC", "SA", "PL", "CLI")

  operator fun invoke(): Flow<Resource<List<Competition>>> = flow {
    emit(Resource.Loading)

    val result = competitionsRepository.getCompetitions()
    if (result is Resource.Success) {
      emit(Resource.Success(getFilteredCompetitions(result.value.competitions)))
    } else if (result is Resource.Failure) {
      emit(result)
    }
  }.flowOn(Dispatchers.IO)

  private fun getFilteredCompetitions(competitions: List<Competition>): List<Competition> {
    // To get only Competitions supported with the free account
    // Free account only get access for some Competitions
    return competitions
      .filter { it.code != null }
      .filter { supportedCompetitions.contains(it.code) }
  }
}