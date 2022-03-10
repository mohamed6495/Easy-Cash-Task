package com.mina_mikhail.base_mvvm.data.competitions.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class TeamEntity(
  @PrimaryKey
  val id: Int,
  val crestUrl: String,
  val name: String,
  val shortName: String
)