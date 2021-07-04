package com.android.designpattern.repository

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.android.designpattern.api.ApiErrorResponse
import com.android.designpattern.api.ApiResponse
import com.android.designpattern.api.ApiSuccessResponse
import com.android.designpattern.model.Resource

 abstract class NetworkBoundResourceOnline<RequestType> {
    private val result = MediatorLiveData<Resource<RequestType>>()

    init {
        setValue(Resource.loading(null))
        fetchFromNetwork()
    }

    @MainThread
    private fun setValue(newValue: Resource<RequestType>) {
        if (result.value != newValue) {
            result.value = newValue
            Log.i("response",newValue.toString())
        }
    }

    private fun fetchFromNetwork() {
        val apiResponse = createCall()
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)

            when (response) {
                is ApiSuccessResponse -> {
                    setValue(Resource.success(processResponse(response)))
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
                    setValue(Resource.error(response.errorMessage, null))

                }
            }
        }
    }

    protected fun onFetchFailed() {
    }

    fun asLiveData() = result as LiveData<Resource<RequestType>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}
