package info.firozansari.redditreader.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RedditKeys(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val after: String?,
    val before: String?
)
