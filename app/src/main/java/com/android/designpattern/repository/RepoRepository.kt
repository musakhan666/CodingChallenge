package com.android.designpattern.repository

import androidx.lifecycle.LiveData
import com.android.designpattern.api.ApiResponse
import com.android.designpattern.api.DesignPatternService
import com.android.designpattern.model.repoItem.Repo
import com.android.designpattern.model.Resource
import javax.inject.Inject

class RepoRepository @Inject constructor(private val service: DesignPatternService) {
    fun fetchRepos(created:String, sort:String, order:String, page:String): LiveData<Resource<Repo>> =
        object  : NetworkBoundResourceOnline<Repo>(){
            override fun createCall(): LiveData<ApiResponse<Repo>> = service.geRepoItems(created,sort,order,page)
        }.asLiveData()
}