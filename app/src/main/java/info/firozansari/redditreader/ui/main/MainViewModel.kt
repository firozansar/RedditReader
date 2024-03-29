package info.firozansari.redditreader.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import info.firozansari.redditreader.data.local.entity.RedditPost
import info.firozansari.redditreader.data.repository.RedditRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val redditRepository: RedditRepository
) : ViewModel() {

    fun fetchPosts(): Flow<PagingData<RedditPost>> {
        return redditRepository.fetchPosts().cachedIn(viewModelScope)
    }
}