package com.mina_mikhail.base_mvvm.presentation.favorites

import com.mina_mikhail.base_mvvm.domain.favorites.repository.FavoritesRepository
import com.mina_mikhail.base_mvvm.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
  private val favoritesRepository: FavoritesRepository
) : BaseViewModel()