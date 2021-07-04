package com.android.designpattern.api

import androidx.lifecycle.LiveData
import com.android.designpattern.model.repoItem.Repo
import retrofit2.http.GET
import retrofit2.http.Query

interface DesignPatternService {



    @GET("repositories?")
    fun geRepoItems(@Query("q") crated:String,
                    @Query("sort") sort:String,
                    @Query("order") order:String,
                    @Query("page") page:String


    ): LiveData<ApiResponse<Repo>>
}