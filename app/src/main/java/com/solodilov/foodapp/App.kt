package com.solodilov.foodapp

import android.app.Application
import com.solodilov.foodapp.di.DaggerApplicationComponent

class App : Application() {

    val appComponent = DaggerApplicationComponent.factory().create(this)
}