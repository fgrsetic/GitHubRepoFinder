package com.franjo.github.data.di

import android.app.Application
import androidx.paging.PagingData
import com.franjo.github.data.network.service.GitHubApiService
import com.franjo.github.data.repository.LoginRepositoryImpl
import com.franjo.github.data.repository.SearchRepositoryImpl
import com.franjo.github.domain.model.repository.Repo
import com.franjo.github.domain.repository.IGithubRepository
import com.franjo.github.domain.repository.ILoginRepository
import com.franjo.github.domain.repository.IUserRepository
import com.franjo.github.domain.shared.DispatcherProvider
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRemoteRepositoryImplSearch(
        dispatcher: DispatcherProvider,
        gitHubApiService: GitHubApiService
    ): IGithubRepository<Flow<PagingData<Repo>>> =
        SearchRepositoryImpl(dispatcher, gitHubApiService)

    @Provides
    @Singleton
    fun provideRemoteRepositoryImplUser(
        dispatcher: DispatcherProvider,
        gitHubApiService: GitHubApiService
    ): IUserRepository = SearchRepositoryImpl(dispatcher, gitHubApiService)


    @Provides
    @Singleton
    fun provideLoginRepositoryImpl(
        app: Application
    ): ILoginRepository = LoginRepositoryImpl(app)

}