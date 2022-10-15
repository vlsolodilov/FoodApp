package com.solodilov.foodapp.domain.usecase

import com.solodilov.foodapp.domain.entity.Meal
import com.solodilov.foodapp.domain.repository.MealRepository
import javax.inject.Inject

class GetMealListUseCase @Inject constructor(private val mealRepository: MealRepository) {

    suspend operator fun invoke(): List<Meal> =
        mealRepository.getMealList()
}