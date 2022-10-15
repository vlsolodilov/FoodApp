package com.solodilov.foodapp.di

import com.solodilov.foodapp.data.network.TheMealDbApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

	private companion object {
		const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
	}

	@Provides
	fun provideGsonConverterFactory(): GsonConverterFactory =
		GsonConverterFactory.create()

	@Provides
	@Singleton
	fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
	): Retrofit =
		Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(gsonConverterFactory)
			.build()

	@Provides
	@Singleton
	fun provideTheMealDbApi(
		retrofit: Retrofit,
	): TheMealDbApi =
		retrofit.create(TheMealDbApi::class.java)
}