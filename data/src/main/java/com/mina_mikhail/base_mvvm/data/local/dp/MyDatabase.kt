package com.mina_mikhail.base_mvvm.data.local.dp

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mina_mikhail.base_mvvm.data.competitions.data_source.local.CompetitionsDao
import com.mina_mikhail.base_mvvm.data.competitions.data_source.local.TeamsDao
import com.mina_mikhail.base_mvvm.data.competitions.entity.CompetitionEntity
import com.mina_mikhail.base_mvvm.data.competitions.entity.TeamEntity

@Database(entities = [TeamEntity::class, CompetitionEntity::class], version = MyDatabase.DATABASE_VERSION)
@TypeConverters(Converters::class)
abstract class MyDatabase : RoomDatabase() {

  companion object {
    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "EasyCashDatabase"
  }

  abstract fun getTeamsDao(): TeamsDao

  abstract fun getCompetitionsDao(): CompetitionsDao
}