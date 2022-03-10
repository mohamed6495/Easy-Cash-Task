package com.mina_mikhail.base_mvvm.core.di.module

import com.mina_mikhail.base_mvvm.data.competitions.data_source.remote.CompetitionsServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkServicesModule {

  @Provides
  @Singleton
  fun provideCompetitionsServices(retrofit: Retrofit): CompetitionsServices =
    retrofit.create(CompetitionsServices::class.java)
}