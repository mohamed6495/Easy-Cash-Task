package com.mina_mikhail.base_mvvm.core.di.module

import com.mina_mikhail.base_mvvm.data.auth.data_source.remote.AuthRemoteDataSource
import com.mina_mikhail.base_mvvm.data.auth.repository.AuthRepositoryImpl
import com.mina_mikhail.base_mvvm.data.competitions.data_source.remote.CompetitionsRemoteDataSource
import com.mina_mikhail.base_mvvm.data.competitions.repository.CompetitionsRepositoryImpl
import com.mina_mikhail.base_mvvm.data.favorites.data_source.local.FavoritesLocalDataSource
import com.mina_mikhail.base_mvvm.data.favorites.repository.FavoritesRepositoryImpl
import com.mina_mikhail.base_mvvm.data.general.repository.GeneralRepositoryImpl
import com.mina_mikhail.base_mvvm.data.local.preferences.AppPreferences
import com.mina_mikhail.base_mvvm.domain.auth.repository.AuthRepository
import com.mina_mikhail.base_mvvm.domain.competitions.repository.CompetitionsRepository
import com.mina_mikhail.base_mvvm.domain.favorites.repository.FavoritesRepository
import com.mina_mikhail.base_mvvm.domain.general.repository.GeneralRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

  @Provides
  @Singleton
  fun provideGeneralRepository(
    appPreferences: AppPreferences
  ): GeneralRepository = GeneralRepositoryImpl(appPreferences)

  @Provides
  @Singleton
  fun provideAuthRepository(
    remoteDataSource: AuthRemoteDataSource,
  ): AuthRepository = AuthRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideSearchRepository(
    remoteDataSource: FavoritesLocalDataSource
  ): FavoritesRepository = FavoritesRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideHomeRepository(
    remoteDataSource: CompetitionsRemoteDataSource
  ): CompetitionsRepository = CompetitionsRepositoryImpl(remoteDataSource)
}