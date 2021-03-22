package info.firozansari.redditreader.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import info.firozansari.redditreader.data.local.entity.RedditKeys

@Dao
interface KeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRedditKeys(redditKey: RedditKeys)

    @Query("SELECT * FROM RedditKeys ORDER BY id DESC")
    suspend fun getRedditKeys(): List<RedditKeys>

}