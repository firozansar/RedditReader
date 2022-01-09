package info.firozansari.redditreader.ui.main

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import info.firozansari.redditreader.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        setupFlowCollector()
        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private fun setupRecyclerView() {
        mainAdapter = MainAdapter { navigateToPost(it.url) }
        binding.postsRecyclerview.apply {
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
        val customTabsIntent = CustomTabsIntent.Builder().build()
        try {
            customTabsIntent.launchUrl(this, Uri.parse(url))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}