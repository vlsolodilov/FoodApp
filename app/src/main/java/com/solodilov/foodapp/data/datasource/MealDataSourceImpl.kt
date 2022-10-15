package com.solodilov.foodapp.data.datasource

import com.solodilov.foodapp.data.model.MealDto
import com.solodilov.foodapp.data.network.TheMealDbApi
import javax.inject.Inject

class MealDataSourceImpl @Inject constructor(
    private val api: TheMealDbApi,
) : MealDataSource {

    override suspend fun getMealDtoList(): List<MealDto> =
        api.getCoinList().meals

}