package info.firozansari.redditreader.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import info.firozansari.redditreader.ui.App
import info.firozansari.redditreader.ui.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class, DatabaseModule::class, ViewModelFactoryModule::class, UiModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

    fun inject(activity: MainActivity)

}
