package com.mina_mikhail.base_mvvm.domain.competitions.entity.response

import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Competition
import com.mina_mikhail.base_mvvm.domain.utils.BaseResponse

data class CompetitionsResponse(
  val competitions: List<Competition>
) : BaseResponse(null)