package info.firozansari.redditreader.di

import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import info.firozansari.redditreader.BuildConfig
import info.firozansari.redditreader.data.local.RedditDatabase
import info.firozansari.redditreader.data.remote.RedditService
import info.firozansari.redditreader.data.repository.RedditRepository
import info.firozansari.redditreader.ui.main.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {

    single {
        Room.databaseBuilder(androidApplication(), RedditDatabase::class.java, "reddit.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {

    single { GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create() }

    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        OkHttpClient.Builder().apply {
            connectTimeout(10L, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(httpLoggingInterceptor)
        }.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(get())
            .build()
    }

    factory {
        get<Retrofit>().create(RedditService::class.java)
    }

}

val repositoryModule = module {
    single { RedditRepository(get(), get()) }
}

val viewModelModule = module {
    single { MainViewModel(get()) }
}