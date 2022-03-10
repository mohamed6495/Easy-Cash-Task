package com.mina_mikhail.base_mvvm.data.competitions.data_source.remote

import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Competition
import com.mina_mikhail.base_mvvm.domain.competitions.entity.response.CompetitionsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CompetitionsServices {

  @GET("competitions")
  suspend fun getCompetitions(): CompetitionsResponse

  @GET("competitions/{competitionID}")
  suspend fun getCompetitionDetails(@Path("competitionID") competitionID: Int): Competition
}