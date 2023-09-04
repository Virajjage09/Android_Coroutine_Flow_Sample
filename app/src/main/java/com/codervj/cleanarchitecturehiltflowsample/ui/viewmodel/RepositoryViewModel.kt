package com.codervj.cleanarchitecturehiltflowsample.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codervj.cleanarchitecturehiltflowsample.domain.GetGithubRepoUseCase
import com.codervj.cleanarchitecturehiltflowsample.ui.state.GetGithubRepoFailure
import com.codervj.cleanarchitecturehiltflowsample.ui.state.GetGithubRepoSuccess
import com.codervj.cleanarchitecturehiltflowsample.ui.state.GithubRepoState
import com.codervj.cleanarchitecturehiltflowsample.ui.state.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(private val getRepositoryUseCase: GetGithubRepoUseCase) : ViewModel() {

    private val userRepositoryData = MutableStateFlow<GithubRepoState>(GetGithubRepoSuccess(arrayListOf()))
    val getUserRepositoryData = userRepositoryData

    fun getRepository(userId: String) {
        userRepositoryData.value = Loading
        viewModelScope.launch {
            Log.d("Coroutine thread", Thread.currentThread().name)
            Log.d("Request for Username", userId)
            getRepositoryUseCase.invoke(userId)
                .catch {
                    it.printStackTrace()
                    userRepositoryData.value = GetGithubRepoFailure(it.message.toString())
                }
                .collect {
                    Log.d("Flow Collect thread", Thread.currentThread().name)
                    userRepositoryData.value = GetGithubRepoSuccess(it)
                }
        }

    }
}
