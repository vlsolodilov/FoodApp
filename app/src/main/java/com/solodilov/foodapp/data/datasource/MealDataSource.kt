package com.solodilov.foodapp.data.datasource

import com.solodilov.foodapp.data.model.MealDto

interface MealDataSource {

    suspend fun getMealDtoList(): List<MealDto>
}