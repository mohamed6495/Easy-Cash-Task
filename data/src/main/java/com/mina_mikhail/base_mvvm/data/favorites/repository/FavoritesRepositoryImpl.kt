package com.mina_mikhail.base_mvvm.data.favorites.repository

import com.mina_mikhail.base_mvvm.data.favorites.data_source.local.FavoritesLocalDataSource
import com.mina_mikhail.base_mvvm.domain.favorites.repository.FavoritesRepository
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(private val remoteDataSource: FavoritesLocalDataSource) :
  FavoritesRepository