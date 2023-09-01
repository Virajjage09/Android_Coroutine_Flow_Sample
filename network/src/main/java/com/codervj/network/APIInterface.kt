package com.codervj.network

import com.codervj.network.models.GithubRepositoryResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface APIInterface {

    @GET("users/{userid}/repos")
    suspend fun getRepositories(@Path("userid") userid: String) : ArrayList<GithubRepositoryResponse>
}