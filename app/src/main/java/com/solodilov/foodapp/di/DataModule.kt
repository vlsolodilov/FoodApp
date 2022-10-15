package com.solodilov.foodapp.di

import com.solodilov.foodapp.data.datasource.MealDataSource
import com.solodilov.foodapp.data.datasource.MealDataSourceImpl
import com.solodilov.foodapp.data.repository.MealRepositoryImpl
import com.solodilov.foodapp.domain.repository.MealRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {

	@Singleton
	@Binds
	fun bindMealDataSource(impl: MealDataSourceImpl): MealDataSource

	@Singleton
	@Binds
	fun bindMealRepository(impl: MealRepositoryImpl): MealRepository

}