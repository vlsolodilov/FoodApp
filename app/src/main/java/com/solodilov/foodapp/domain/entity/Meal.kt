package com.solodilov.foodapp.domain.entity

import kotlin.random.Random

data class Meal(
    val name: String,
    val description: String,
    val image: String,
    val price: Int = Random.nextInt(100, 1000),
)
