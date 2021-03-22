package info.firozansari.redditreader.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import info.firozansari.redditreader.data.local.entity.RedditPost

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePosts(redditPosts: List<RedditPost>)

    @Query("SELECT * FROM RedditPost")
    fun getPosts(): PagingSource<Int, RedditPost>
}