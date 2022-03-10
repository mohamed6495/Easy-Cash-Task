package com.mina_mikhail.base_mvvm.data.competitions.mapper

import com.mina_mikhail.base_mvvm.data.competitions.entity.TeamEntity
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Team

object TeamMapper {

  fun List<TeamEntity>.mapToUiModelList(): List<Team> {
    val teams: ArrayList<Team> = ArrayList()

    forEach {
      teams.add(Team(it.id, it.crestUrl, it.name, it.shortName))
    }

    return teams
  }

  fun TeamEntity?.mapToUiMode(): Team? {
    if (this == null) {
      return null
    } else {
      return Team(id, crestUrl, name, shortName)
    }
  }

  fun Team.mapToEntity(): TeamEntity {
    return TeamEntity(id, crestUrl, name, shortName)
  }
}