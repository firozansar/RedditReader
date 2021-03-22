package info.firozansari.redditreader.data.remote

import com.google.gson.annotations.SerializedName
import info.firozansari.redditreader.data.local.entity.RedditPost

data class PostData(
    @SerializedName("author") val author : String,
    @SerializedName("title") val title : String,
    @SerializedName("url") val url: String
)

fun PostData.toRedditPost() = RedditPost(this.title, this.author, this.url)

