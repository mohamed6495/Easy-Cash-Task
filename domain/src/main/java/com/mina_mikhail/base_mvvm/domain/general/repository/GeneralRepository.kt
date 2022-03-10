package com.mina_mikhail.base_mvvm.domain.general.repository

interface GeneralRepository {

  fun isFirstTime(): Boolean

  fun setFirstTime(isFirstTime: Boolean)
}