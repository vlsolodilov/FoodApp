package com.solodilov.foodapp.data.model


import com.google.gson.annotations.SerializedName

data class MealsDto(
    @SerializedName("meals")
    val meals: List<MealDto>
)