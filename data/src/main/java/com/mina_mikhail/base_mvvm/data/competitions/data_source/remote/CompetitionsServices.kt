package com.mina_mikhail.base_mvvm.data.competitions.data_source.remote

import com.mina_mikhail.base_mvvm.domain.competitions.entity.response.CompetitionsResponse
import retrofit2.http.GET

interface CompetitionsServices {

  @GET("competitions")
  suspend fun getCompetitions(): CompetitionsResponse
}