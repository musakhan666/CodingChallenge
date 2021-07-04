/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.designpattern.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.android.designpattern.AppExecutors
import com.android.designpattern.api.ApiEmptyResponse
import com.android.designpattern.api.ApiErrorResponse
import com.android.designpattern.api.ApiResponse
import com.android.designpattern.api.ApiSuccessResponse
import com.android.designpattern.model.Resource


/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 *
 *
 * You can read more about it in the [Architecture
 * Guide](https://developer.android.com/arch).
 * @param <ResultType>
 * @param <RequestType>
</RequestType></ResultType> */
abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
      //  result.value = Resource.loading(null)
        fetchFromNetwork()
//        @Suppress("LeakingThis")
      //  val dbSource = loadFromDb()
//        val networkSource = createCall()
//        result.addSource(networkSource){
//            data->
//            if (shouldFetch(data)) {
//                fetchFromNetwork(dbSource)
//            } else {
//                result.addSource(dbSource) { newData ->
//                    setValue(Resource.success(newData))
//                }
//            }
//        }

//        result.addSource(dbSource) { data ->
//            result.removeSource(dbSource)

     //   }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork(/*dbSource: LiveData<ResultType>*/) {
        val apiResponse = createCall()

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            when (response) {

                is ApiSuccessResponse -> {
                    appExecutors.networkIO().execute {
                    //    saveCallResult(processResponse(response))
                        appExecutors.mainThread().execute {
                            Log.i("live data", ""+Resource.success(result.value).data.toString())
                            // we specially request a new live data,
                            // otherwise we will get immediately last cached value,
                            // which may not be updated with latest results received from network.
//                            result.addSource(loadFromDb()) { newData ->
//                                setValue(Resource.success(newData))
//                            }

                        }
                    }
                }
                is ApiEmptyResponse -> {
                    appExecutors.mainThread().execute {
                        // reload from disk whatever we had
//                        result.addSource(loadFromDb()) { newData ->
//                            setValue(Resource.success(newData))
                      //  Log.i("live data1", ""+Resource.success(result.value).data.toString())
//                        }
                    }
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
//                    result.addSource(dbSource) { newData ->
//                        setValue(Resource.error(response.errorMessage, newData))
//                    }
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body
//
//    @WorkerThread
//    protected abstract fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

//    @MainThread
//    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}
