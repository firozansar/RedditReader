package info.firozansari.redditreader.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RedditPost(
    @PrimaryKey
    val title: String,
    val author: String,
    val url: String
)