package com.mina_mikhail.base_mvvm.core.di.module

import com.mina_mikhail.base_mvvm.domain.auth.repository.AuthRepository
import com.mina_mikhail.base_mvvm.domain.auth.use_case.LogInUseCase
import com.mina_mikhail.base_mvvm.domain.competitions.repository.CompetitionsRepository
import com.mina_mikhail.base_mvvm.domain.competitions.use_case.AddRemoveCompetitionToFavoritesUseCase
import com.mina_mikhail.base_mvvm.domain.competitions.use_case.AddRemoveTeamToFavoritesUseCase
import com.mina_mikhail.base_mvvm.domain.competitions.use_case.GetCompetitionDetailsUseCase
import com.mina_mikhail.base_mvvm.domain.competitions.use_case.GetCompetitionsUseCase
import com.mina_mikhail.base_mvvm.domain.competitions.use_case.GetTeamDetailsUseCase
import com.mina_mikhail.base_mvvm.domain.general.repository.GeneralRepository
import com.mina_mikhail.base_mvvm.domain.general.use_case.CheckFirstTimeUseCase
import com.mina_mikhail.base_mvvm.domain.general.use_case.GeneralUseCases
import com.mina_mikhail.base_mvvm.domain.general.use_case.SetFirstTimeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

  /* GENERAL */
  @Provides
  @Singleton
  fun provideCheckFirstTimeUseCase(
    generalRepository: GeneralRepository
  ): CheckFirstTimeUseCase = CheckFirstTimeUseCase(generalRepository)

  @Provides
  @Singleton
  fun provideSetFirstTimeUseCase(
    generalRepository: GeneralRepository
  ): SetFirstTimeUseCase = SetFirstTimeUseCase(generalRepository)

  @Provides
  @Singleton
  fun provideGeneralUseCases(
    checkFirstTimeUseCase: CheckFirstTimeUseCase,
    setFirstTimeUseCase: SetFirstTimeUseCase,
  ): GeneralUseCases =
    GeneralUseCases(checkFirstTimeUseCase, setFirstTimeUseCase)

  /* LOG IN */
  @Provides
  @Singleton
  fun provideLogInUseCase(
    authRepository: AuthRepository,
  ): LogInUseCase = LogInUseCase(authRepository)

  /* COMPETITIONS */
  @Provides
  @Singleton
  fun provideGetCompetitionsUseCase(
    competitionsRepository: CompetitionsRepository
  ): GetCompetitionsUseCase = GetCompetitionsUseCase(competitionsRepository)

  @Provides
  @Singleton
  fun provideGetCompetitionDetailsUseCase(
    competitionsRepository: CompetitionsRepository
  ): GetCompetitionDetailsUseCase = GetCompetitionDetailsUseCase(competitionsRepository)

  @Provides
  @Singleton
  fun provideGetTeamDetailsUseCase(
    competitionsRepository: CompetitionsRepository
  ): GetTeamDetailsUseCase = GetTeamDetailsUseCase(competitionsRepository)

  @Provides
  @Singleton
  fun provideAddRemoveTeamToFavoritesUseCase(
    competitionsRepository: CompetitionsRepository
  ): AddRemoveTeamToFavoritesUseCase = AddRemoveTeamToFavoritesUseCase(competitionsRepository)

  @Provides
  @Singleton
  fun provideAddRemoveCompetitionToFavoritesUseCase(
    competitionsRepository: CompetitionsRepository
  ): AddRemoveCompetitionToFavoritesUseCase = AddRemoveCompetitionToFavoritesUseCase(competitionsRepository)
}