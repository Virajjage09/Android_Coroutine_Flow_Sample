package com.codervj.cleanarchitecturehiltflowsample.data

import android.util.Log
import com.codervj.cleanarchitecturehiltflowsample.data.mapper.RepoDataMapper
import com.codervj.cleanarchitecturehiltflowsample.domain.models.RepoDataRecord
import com.codervj.network.APIInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(private val api: APIInterface) : DataRepository {
    override fun getGitHubRepositories(userId: String) = flow {
        Log.d("FLow Emit Thread", Thread.currentThread().name)
        emit(api.getRepositories(userId))

    }.map { response ->
        val dataList = arrayListOf<RepoDataRecord>()
        response.forEach {
            dataList.add(RepoDataMapper.mapToRepoDataRecord(
                it.id, it.name, it.url, it.description, it.visibility
            ))
        }
        dataList
    }
}