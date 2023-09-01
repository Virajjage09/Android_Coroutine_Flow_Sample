package com.codervj.cleanarchitecturehiltflowsample.data

import com.codervj.cleanarchitecturehiltflowsample.domain.models.RepoDataRecord
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    fun getGitHubRepositories(userId: String) : Flow<ArrayList<RepoDataRecord>>
}