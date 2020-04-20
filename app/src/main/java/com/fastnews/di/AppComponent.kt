package com.fastnews.di

import com.fastnews.repository.PostRepository
import com.fastnews.repository.PostRepositoryImpl
import com.fastnews.service.api.RedditAPI
import com.fastnews.service.api.RedditService
import com.fastnews.viewmodel.PostViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppComponent {
    val appModule = module {
        // Api
        single<RedditService> { RedditAPI.redditService }
        // Repository
        single<PostRepository> { PostRepositoryImpl(get()) }
        // View Model
        viewModel { PostViewModel(get()) }
    }
}