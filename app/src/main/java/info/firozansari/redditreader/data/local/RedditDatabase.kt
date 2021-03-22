package info.firozansari.redditreader.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import info.firozansari.redditreader.data.local.dao.KeysDao
import info.firozansari.redditreader.data.local.dao.PostsDao
import info.firozansari.redditreader.data.local.entity.RedditKeys
import info.firozansari.redditreader.data.local.entity.RedditPost
import info.firozansari.redditreader.util.Constant.DATABASE_VERSION
import info.firozansari.redditreader.util.Constant.EXPORT_SCHEMA

@Database(entities = [RedditPost::class, RedditKeys::class], version = DATABASE_VERSION, exportSchema = EXPORT_SCHEMA)
abstract class RedditDatabase : RoomDatabase() {
    abstract fun postsDao() : PostsDao
    abstract fun keysDao(): KeysDao
}