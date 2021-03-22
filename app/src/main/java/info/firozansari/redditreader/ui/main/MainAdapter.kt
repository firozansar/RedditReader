package info.firozansari.redditreader.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import info.firozansari.redditreader.data.local.entity.RedditPost
import info.firozansari.redditreader.databinding.ListItemPostBinding

class MainAdapter(val postClick: (RedditPost) -> Unit) :
    PagingDataAdapter<RedditPost, MainAdapter.PostViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        getItem(position)?.let { post ->
            holder.bindTo(post)
            holder.itemView.setOnClickListener { postClick(post) }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ListItemPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PostViewHolder(binding)
    }

    class PostViewHolder(private val binding: ListItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(redditPost: RedditPost) {
            with(redditPost) {
                binding.postTitle.text =  title
                binding.authorName.text = "by $author"
            }
        }
    }
}


val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RedditPost>() {
    override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.author == newItem.author
    }

    override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
        return oldItem == newItem
    }
}
