package info.firozansari.redditreader.di

import dagger.Module
import info.firozansari.redditreader.ui.main.MainActivity

@Module
abstract class UiModule {

    // Activities

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}