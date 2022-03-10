package com.mina_mikhail.base_mvvm.data.local.dp

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mina_mikhail.base_mvvm.domain.competitions.entity.model.Season
import java.lang.reflect.Type

class Converters {

  @TypeConverter
  fun fromSeasonString(value: String): Season {
    val modelType: Type = object : TypeToken<Season>() {}.type
    return Gson().fromJson(value, modelType)
  }

  @TypeConverter
  fun fromSeasonModel(model: Season): String {
    val gson = Gson()
    return gson.toJson(model)
  }
}