package info.firozansari.redditreader.data.remote

import com.google.gson.annotations.SerializedName

data class PostData(
    @SerializedName("author") val author : String,
    @SerializedName("title") val title : String,
    @SerializedName("url") val url: String
)

