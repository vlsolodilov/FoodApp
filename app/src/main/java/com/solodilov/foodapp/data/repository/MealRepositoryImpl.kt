package com.solodilov.foodapp.data.repository

import com.solodilov.foodapp.data.datasource.MealDataSource
import com.solodilov.foodapp.data.mapper.MealMapper
import com.solodilov.foodapp.domain.entity.Meal
import com.solodilov.foodapp.domain.repository.MealRepository
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val dataSource: MealDataSource,
    private val mapper: MealMapper,
) : MealRepository {

    override suspend fun getMealList(): List<Meal> =
        dataSource.getMealDtoList().map { mealDto ->
            mapper.mapMealDtoToMeal(mealDto)
        }

}