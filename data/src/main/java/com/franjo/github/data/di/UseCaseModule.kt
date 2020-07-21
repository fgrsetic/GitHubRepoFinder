package com.franjo.github.data.di

import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetRepositoryListItems(
        dispatcherProvider: DispatcherProvider
    ) = GetRepositoryListItems(dispatcherProvider)

}