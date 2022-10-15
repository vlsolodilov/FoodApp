package com.solodilov.foodapp.domain.repository

import com.solodilov.foodapp.domain.entity.Meal

interface MealRepository {

    suspend fun getMealList(): List<Meal>
}