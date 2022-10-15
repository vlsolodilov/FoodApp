package com.solodilov.foodapp.data.mapper

import com.solodilov.foodapp.data.model.MealDto
import com.solodilov.foodapp.domain.entity.Meal
import javax.inject.Inject

class MealMapper @Inject constructor() {

    fun mapMealDtoToMeal(mealDto: MealDto): Meal =
        Meal(
            name = mealDto.strMeal,
            description = mealDto.strInstructions,
            image = mealDto.strMealThumb
        )
}