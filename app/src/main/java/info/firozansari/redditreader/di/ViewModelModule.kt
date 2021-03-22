package info.firozansari.redditreader.di

import info.firozansari.redditreader.ui.main.MainViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { MainViewModel(get()) }
}