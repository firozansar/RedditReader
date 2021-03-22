package info.firozansari.redditreader.di

import info.firozansari.redditreader.data.repository.RedditRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { RedditRepository(get(), get()) }
}