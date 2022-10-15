package com.solodilov.foodapp.presentation.menu

import com.solodilov.foodapp.domain.entity.Meal

sealed class MenuScreenState {

    object Loading : MenuScreenState()
    data class Content(val content: List<Meal>) : MenuScreenState()
    object Error : MenuScreenState()

}