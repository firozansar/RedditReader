package info.firozansari.redditreader.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface RedditService {

    @GET("r/Android/hot.json")
    suspend fun getPosts(
        @Query("limit") loadSize: Int = 0,
        @Query("after") after: String? = null,
        @Query("before") before: String? = null
    ): RedditResponse
}