package com.mina_mikhail.base_mvvm.domain.competitions.entity.model

data class Team(
  val address: String,
  val area: Area?,
  val clubColors: String,
  val crestUrl: String,
  val id: Int,
  val name: String,
  val shortName: String,
  val squad: List<Player>,
  var isFavourite: Boolean
) {
  constructor(id: Int, crestUrl: String, name: String, shortName: String) : this(
    "",
    null,
    "",
    crestUrl,
    id,
    name,
    shortName,
    emptyList(),
    false
  )
}