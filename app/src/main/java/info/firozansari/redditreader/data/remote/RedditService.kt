package info.firozansari.redditreader.data.remote

import info.firozansari.redditreader.util.Constant.GET_POSTS_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditService {

    @GET(GET_POSTS_ENDPOINT)
    suspend fun getPosts(
        @Query("limit") loadSize: Int = 0,
        @Query("after") after: String? = null,
        @Query("before") before: String? = null
    ): RedditResponse

}