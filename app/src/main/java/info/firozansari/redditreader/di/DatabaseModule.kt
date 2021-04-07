package info.firozansari.redditreader.di

import androidx.room.Room
import info.firozansari.redditreader.data.local.RedditDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(androidApplication(), RedditDatabase::class.java, "reddit.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}