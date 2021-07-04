package com.android.designpattern.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.android.designpattern.model.repoItem.Repo
import com.android.designpattern.model.Resource
import com.android.designpattern.repository.RepoRepository
import javax.inject.Inject

class RepoViewModel @Inject constructor(val repository: RepoRepository) : ViewModel() {
    fun fetchContacts(created:String,sort:String,order:String,page:String): LiveData<Resource<Repo>>{
        return repository.fetchRepos(created, sort, order, page)
    }
}