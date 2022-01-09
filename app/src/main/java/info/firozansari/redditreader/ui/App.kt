package info.firozansari.redditreader.ui

import android.app.Activity
import android.app.Application
import info.firozansari.redditreader.di.AppComponent
import info.firozansari.redditreader.di.DaggerAppComponent
import javax.inject.Inject

open class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    protected open fun initAppComponent() {
        val appComponent = DaggerAppComponent
            .builder()
            .app(this)
            .build()

        initAppComponent(appComponent)
    }

    fun initAppComponent(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }
}
