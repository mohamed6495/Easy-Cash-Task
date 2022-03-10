package com.mina_mikhail.base_mvvm.data.competitions.data_source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mina_mikhail.base_mvvm.data.competitions.entity.TeamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamsDao {

  @Query("SELECT * FROM teams")
  fun getAll(): Flow<List<TeamEntity>>

  @Query("SELECT * FROM teams WHERE id = :teamID")
  fun getTeamById(teamID: Int): Flow<TeamEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(team: TeamEntity)

  @Delete
  suspend fun delete(team: TeamEntity)
}