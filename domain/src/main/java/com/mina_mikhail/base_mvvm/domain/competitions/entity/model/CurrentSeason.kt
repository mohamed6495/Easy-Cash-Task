package com.mina_mikhail.base_mvvm.domain.competitions.entity.model

data class CurrentSeason(
  val currentMatchday: Int,
  val endDate: String,
  val id: Int,
  val startDate: String,
  val winner: Any
)