package com.mina_mikhail.base_mvvm.domain.competitions.entity.response

import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Competition
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Season
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Team
import com.mina_mikhail.base_mvvm.domain.utils.BaseResponse

data class CompetitionTeamsResponse(
  val competition: Competition,
  val season: Season,
  val teams: List<Team>
) : BaseResponse(null)