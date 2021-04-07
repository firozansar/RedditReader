package info.firozansari.redditreader.ui

import android.app.Application
import info.firozansari.redditreader.di.databaseModule
import info.firozansari.redditreader.di.networkModule
import info.firozansari.redditreader.di.repositoryModule
import info.firozansari.redditreader.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            printLogger()
            androidContext(this@App)
            modules(listOf(networkModule, databaseModule, repositoryModule, viewModelModule))
        }
    }
}
