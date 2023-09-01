package com.codervj.cleanarchitecturehiltflowsample.domain.models

data class RepoDataRecord(
    var id : Int = 0,
    var name : String = "",
    var url : String = "",
    var description : String? = "",
    var visibility: String = ""
)
