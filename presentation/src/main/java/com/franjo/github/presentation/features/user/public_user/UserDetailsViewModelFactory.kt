package com.franjo.github.presentation.features.user.public_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.franjo.github.domain.shared.DispatcherProvider
import com.franjo.github.domain.usecase.GetUserData
import com.franjo.github.presentation.model.RepositoryUI
import com.franjo.github.presentation.util.UserDataPresentation

class UserDetailsViewModelFactory (
    private val repository: RepositoryUI,
    private val dispatcherProvider: DispatcherProvider,
    private val userDataPresentation: UserDataPresentation,
    private val getUserData: GetUserData
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserDetailsViewModel::class.java)) {
            return UserDetailsViewModel(
                repository,
                dispatcherProvider,
                userDataPresentation,
                getUserData
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}