package com.mina_mikhail.base_mvvm.domain.competitions.entity.model

data class Player(
  val countryOfBirth: String,
  val dateOfBirth: String,
  val id: Int,
  val name: String,
  val nationality: String,
  val position: String,
  val role: String,
)