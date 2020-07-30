package com.franjo.github.presentation.features.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.franjo.github.domain.model.repository.Repo
import com.franjo.github.domain.service.ISharedPrefsService
import com.franjo.github.domain.shared.DispatcherProvider
import com.franjo.github.domain.shared.SORT_REPO_KEY
import com.franjo.github.domain.shared.SORT_STARS
import com.franjo.github.domain.usecase.GetSearchedRepositories
import com.franjo.github.domain.usecase.LoginStart
import com.franjo.github.presentation.BaseViewModel
import com.franjo.github.presentation.features.user_details.LoadingApiStatus
import com.franjo.github.presentation.model.RepositoryUI
import com.franjo.github.presentation.model.asPresentationModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchRepositoryViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val sharedPrefsService: ISharedPrefsService,
    private val getSearchedRepositories: GetSearchedRepositories<Flow<PagingData<Repo>>>,
    private val requestLogin: LoginStart
) : BaseViewModel(dispatcherProvider) {

    private val _navigateToRepositoryDetails = MutableLiveData<RepositoryUI>()
    val navigateToRepositoryDetails: LiveData<RepositoryUI> get() = _navigateToRepositoryDetails

    private val _navigateToUserDetails = MutableLiveData<RepositoryUI>()
    val navigateToUserDetails: LiveData<RepositoryUI> get() = _navigateToUserDetails

    private val _status = MutableLiveData<LoadingApiStatus>()
    val status: LiveData<LoadingApiStatus> get() = _status

    fun searchRepository(queryString: String): Flow<PagingData<RepositoryUI>> {
        val sortBy = sharedPrefsService.getValue(SORT_REPO_KEY, SORT_STARS)
        return getSearchedRepositories.getSearchResultStream(queryString, sortBy as String)
            .map { pagingData ->
                pagingData.map {
                    it.asPresentationModel()
                }
                // cachedIn() method that allows us to cache the content of a Flow<PagingData> in a CoroutineScope
                // If we're doing any operations on the Flow, like map or filter,
                // we need to call cachedIn after we execute these operations to ensure we don't need to trigger them again
            }.cachedIn(viewModelScope).catch { cause: Throwable -> cause.message }
    }

    // Navigation
    fun toRepositoryDetailsNavigate(repository: RepositoryUI) {
        _navigateToRepositoryDetails.value = repository
    }

    fun onRepositoryDetailsNavigated() {
        _navigateToRepositoryDetails.value = null
    }

    fun toUserDetailsNavigate(repository: RepositoryUI) {
        _navigateToUserDetails.value = repository
    }

    fun onUserDetailsNavigated() {
        _navigateToUserDetails.value = null
    }

    fun login() {
        viewModelScope.launch {
            requestLogin.execute()
        }
    }
}