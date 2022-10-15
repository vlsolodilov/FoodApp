package com.solodilov.foodapp.presentation.menu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solodilov.foodapp.domain.usecase.GetMealListUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

class MenuViewModel @Inject constructor(
    private val getMealListUseCase: GetMealListUseCase,
) : ViewModel() {

    private val _state = MutableLiveData<MenuScreenState>(MenuScreenState.Loading)
    val state: LiveData<MenuScreenState> = _state

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    init {
        loadMealList()
    }

    fun loadMealList() {

        _state.value = MenuScreenState.Loading
        viewModelScope.launch(exceptionHandler) {
            _state.postValue(MenuScreenState.Content(content = getMealListUseCase()))
        }
    }

    private fun handleError(error: Throwable) {
        Log.d(TAG, "handleError: ${error.message}")
        _state.value = MenuScreenState.Error
    }

    companion object {
        const val TAG = "TAG"
    }
}