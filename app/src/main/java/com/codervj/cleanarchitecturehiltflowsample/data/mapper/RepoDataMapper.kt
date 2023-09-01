package com.codervj.cleanarchitecturehiltflowsample.data.mapper

import com.codervj.cleanarchitecturehiltflowsample.domain.models.RepoDataRecord

internal object RepoDataMapper {
    fun mapToRepoDataRecord(
        id: Int,
        name: String,
        url: String,
        description: String?,
        visibility: String
    ): RepoDataRecord {
        return RepoDataRecord(id, name, url, description, visibility)
    }
}