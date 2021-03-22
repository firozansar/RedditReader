package info.firozansari.redditreader.ui.main

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import info.firozansari.redditreader.R
import info.firozansari.redditreader.databinding.ActivityMainBinding
import info.firozansari.redditreader.util.DividerItemDecorator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel by viewModel<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        setupFlowCollector()
    }

    private fun setupRecyclerView() {
        mainAdapter = MainAdapter { navigateToPost(it.url) }
        binding.postsRecyclerview.apply {
            addItemDecoration(
                DividerItemDecorator(
                    ContextCompat.getDrawable(
                        this@MainActivity,
                        R.drawable.divider
                    )!!
                )
            )
            adapter = mainAdapter
        }
    }

    private fun setupFlowCollector() {
        lifecycleScope.launch {
            mainViewModel.fetchPosts().collectLatest {
                mainAdapter.submitData(it)
            }
        }
    }

    private fun navigateToPost(url: String) {
        val builder = CustomTabsIntent.Builder();
        val customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));

    }
}