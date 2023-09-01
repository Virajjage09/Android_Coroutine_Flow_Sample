package com.codervj.cleanarchitecturehiltflowsample.ui.state

import com.codervj.cleanarchitecturehiltflowsample.domain.models.RepoDataRecord

sealed class GithubRepoState
data class GetGithubRepoSuccess(val data: ArrayList<RepoDataRecord>) : GithubRepoState()
data class GetGithubRepoFailure(val message: String) : GithubRepoState()
object Loading : GithubRepoState()


