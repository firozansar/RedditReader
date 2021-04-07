package info.firozansari.redditreader.data.remote

import info.firozansari.redditreader.data.local.entity.RedditPost

data class PostData(val author: String, val title: String, val url: String)

fun PostData.toRedditPost() = RedditPost(this.title, this.author, this.url)