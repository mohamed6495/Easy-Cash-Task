package com.mina_mikhail.base_mvvm.data.general.repository

import com.mina_mikhail.base_mvvm.data.local.preferences.AppPreferences
import com.mina_mikhail.base_mvvm.domain.general.repository.GeneralRepository
import javax.inject.Inject

class GeneralRepositoryImpl @Inject constructor(
  private val appPreferences: AppPreferences
) : GeneralRepository {

  override
  fun isFirstTime() = appPreferences.isFirstTime

  override
  fun setFirstTime(isFirstTime: Boolean) {
    appPreferences.isFirstTime = isFirstTime
  }
}