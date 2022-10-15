package com.solodilov.foodapp.di

import android.app.Application
import com.solodilov.foodapp.presentation.menu.MenuFragment
import com.solodilov.foodapp.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
	DataModule::class,
	NetworkModule::class,
	ViewModelModule::class,
])
interface ApplicationComponent {

	fun inject(activity: MainActivity)
	fun inject(fragment: MenuFragment)

	@Component.Factory
	interface Factory {
		fun create(
			@BindsInstance application: Application,
		): ApplicationComponent
	}
}