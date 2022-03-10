package com.mina_mikhail.base_mvvm.domain.competitions.entity.model

data class Competition(
  val area: Area,
  val code: String?,
  val currentSeason: Season,
  val emblemUrl: Any,
  val id: Int,
  val lastUpdated: String,
  val name: String,
  val numberOfAvailableSeasons: Int,
  val plan: String,
  val seasons: List<Season>
)