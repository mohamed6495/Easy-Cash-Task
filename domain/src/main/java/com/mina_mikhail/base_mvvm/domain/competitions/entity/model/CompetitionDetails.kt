package com.mina_mikhail.base_mvvm.domain.competitions.entity.model

data class CompetitionDetails(
  var competition: Competition,
  var teams: List<Team>
)