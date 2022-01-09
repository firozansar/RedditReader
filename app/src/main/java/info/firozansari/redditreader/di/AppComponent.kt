package info.firozansari.redditreader.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import info.firozansari.redditreader.ui.App
import info.firozansari.redditreader.ui.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class, ViewModelModule::class, UiModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun app(app: App): Builder

        fun build(): AppComponent
    }

    // Injection Methods

    fun inject(app: App)
}
