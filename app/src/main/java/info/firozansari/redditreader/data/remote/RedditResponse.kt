package info.firozansari.redditreader.data.remote

import com.google.gson.annotations.SerializedName

data class RedditResponse(
    @SerializedName("kind") val kind: String,
    @SerializedName("data") val data: Data
)