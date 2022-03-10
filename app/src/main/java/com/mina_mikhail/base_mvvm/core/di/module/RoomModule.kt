package com.mina_mikhail.base_mvvm.core.di.module

import android.content.Context
import androidx.room.Room
import com.mina_mikhail.base_mvvm.data.local.dp.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

  @Provides
  @Singleton
  fun provideDatabase(@ApplicationContext context: Context): MyDatabase =
    Room.databaseBuilder(context, MyDatabase::class.java, MyDatabase.DATABASE_NAME).build()
}