package com.solodilov.foodapp.data.network

import com.solodilov.foodapp.data.model.MealsDto
import retrofit2.http.GET

interface TheMealDbApi {

    @GET("search.php?s=pie")
    suspend fun getCoinList(): MealsDto

}