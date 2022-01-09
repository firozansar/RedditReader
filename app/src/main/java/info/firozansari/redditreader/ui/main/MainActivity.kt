package info.firozansari.redditreader.ui.main

import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import info.firozansari.redditreader.databinding.ActivityMainBinding
import info.firozansari.redditreader.di.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
    }
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