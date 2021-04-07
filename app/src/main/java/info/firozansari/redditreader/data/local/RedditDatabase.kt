package info.firozansari.redditreader.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import info.firozansari.redditreader.data.local.dao.KeysDao
import info.firozansari.redditreader.data.local.dao.PostsDao
import info.firozansari.redditreader.data.local.entity.RedditKeys
import info.firozansari.redditreader.data.local.entity.RedditPost

@Database(entities = [RedditPost::class, RedditKeys::class], version = 1, exportSchema = false)
abstract class RedditDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
    abstract fun keysDao(): KeysDao
}