package com.codervj.cleanarchitecturehiltflowsample.domain

import android.util.Log
import com.codervj.cleanarchitecturehiltflowsample.data.DataRepository
import com.codervj.cleanarchitecturehiltflowsample.domain.models.RepoDataRecord
import com.codervj.network.models.GithubRepositoryResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGithubRepoUseCase @Inject constructor (private val repository: DataRepository) {

    operator fun invoke(userId: String): Flow<ArrayList<RepoDataRecord>> {
        Log.d("Flow Invoked thread", Thread.currentThread().name)
        return repository.getGitHubRepositories(userId)
    }
}