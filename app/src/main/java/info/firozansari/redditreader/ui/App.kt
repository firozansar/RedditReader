package info.firozansari.redditreader.ui

import android.app.Activity
import android.app.Application
import info.firozansari.redditreader.di.AppComponent
import info.firozansari.redditreader.di.DaggerAppComponent
import javax.inject.Inject

open class App : Application(){


    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    protected open fun initAppComponent() {
        val appComponent: AppComponent by lazy {
            DaggerAppComponent.builder().application(this).build()
        }
    }

}
