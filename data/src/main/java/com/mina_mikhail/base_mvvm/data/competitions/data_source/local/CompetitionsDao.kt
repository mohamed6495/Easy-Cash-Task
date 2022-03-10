package com.mina_mikhail.base_mvvm.data.competitions.data_source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mina_mikhail.base_mvvm.data.competitions.entity.CompetitionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CompetitionsDao {

  @Query("SELECT * FROM competitions")
  fun getAll(): Flow<List<CompetitionEntity>>

  @Query("SELECT * FROM competitions WHERE id = :competitionID")
  fun getCompetitionById(competitionID: Int): Flow<CompetitionEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(competition: CompetitionEntity)

  @Delete
  suspend fun delete(competition: CompetitionEntity)
}