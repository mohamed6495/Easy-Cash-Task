package com.mina_mikhail.base_mvvm.domain.competitions.entity.model

data class Team(
  val address: String,
  val area: Area,
  val clubColors: String,
  val crestUrl: String,
  val email: String,
  val founded: Int,
  val id: Int,
  val lastUpdated: String,
  val name: String,
  val phone: String,
  val shortName: String,
  val tla: String,
  val venue: String,
  val website: String
)