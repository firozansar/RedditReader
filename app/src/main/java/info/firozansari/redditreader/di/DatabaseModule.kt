package info.firozansari.redditreader.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import info.firozansari.redditreader.data.local.RedditDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application): RedditDatabase {
        return Room.databaseBuilder(
            app,
            RedditDatabase::class.java, "reddit.db"
        ).build()
    }
}