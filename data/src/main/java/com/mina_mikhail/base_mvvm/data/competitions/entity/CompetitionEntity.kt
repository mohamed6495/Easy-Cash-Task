package com.mina_mikhail.base_mvvm.data.competitions.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Season

@Entity(tableName = "competitions")
data class CompetitionEntity(
  @PrimaryKey
  val id: Int,
  val code: String?,
  val currentSeason: Season,
  val name: String,
)