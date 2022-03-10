package com.mina_mikhail.base_mvvm.presentation.base.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun Any.toJsonString(): String = Gson().toJson(this)

fun <A : Any> String.toJsonModel(modelClass: Class<A>): A = Gson().fromJson(this, modelClass)

inline fun <reified T> String.toJsonList() = Gson().fromJson<T>(this, object : TypeToken<T>() {}.type)