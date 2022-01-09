package info.firozansari.redditreader.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import info.firozansari.redditreader.data.local.RedditDatabase
import info.firozansari.redditreader.data.local.entity.RedditPost
import info.firozansari.redditreader.data.remote.RedditRemoteMediator
import info.firozansari.redditreader.data.remote.RedditService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RedditRepository @Inject constructor(
    private val redditService: RedditService,
    private val redditDatabase: RedditDatabase
) {

    @OptIn(ExperimentalPagingApi::class)
    fun fetchPosts(): Flow<PagingData<RedditPost>> {
        return Pager(
            PagingConfig(
                pageSize = 40,
                enablePlaceholders = false,
                prefetchDistance = 3
            ),

            remoteMediator = RedditRemoteMediator(redditService, redditDatabase),
            pagingSourceFactory = { redditDatabase.postsDao().getPosts() }

        ).flow
    }
}