package com.mina_mikhail.base_mvvm.domain.competitions.entity.model

data class Competition(
  val area: Area?,
  val code: String?,
  val currentSeason: Season,
  val id: Int,
  val name: String,
  val seasons: List<Season>,
  var isFavourite: Boolean
) {
  constructor(id: Int, code: String?, currentSeason: Season, name: String) : this(
    null,
    code,
    currentSeason,
    id,
    name,
    emptyList(),
    false
  )
}