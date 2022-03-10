package com.mina_mikhail.base_mvvm.data.competitions.mapper

import com.mina_mikhail.base_mvvm.data.competitions.entity.CompetitionEntity
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Competition

object CompetitionsMapper {

  fun List<CompetitionEntity>.mapToUiModelList(): List<Competition> {
    val competitions: ArrayList<Competition> = ArrayList()

    forEach {
      competitions.add(Competition(it.id, it.code, it.currentSeason, it.name))
    }

    return competitions
  }

  fun CompetitionEntity.mapToUiModel(): Competition {
    return Competition(id, code, currentSeason, name)
  }

  fun Competition.mapToEntity(): CompetitionEntity {
    return CompetitionEntity(id, code, currentSeason, name)
  }
}