package com.adrian.githubapp.model.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adrian.githubapp.api.ApiConfig
import com.adrian.githubapp.model.DetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailViewModel : ViewModel() {

    private val listDetail = MutableLiveData<DetailResponse>()
    val detail: LiveData<DetailResponse> = listDetail

    private val isLoading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = isLoading

    companion object {
        private const val TAG = "UserDetailModel"
    }

    fun getGithubUser(login: String) {
        isLoading.value = true
        ApiConfig.getApiService().getUserDetail(login).enqueue(object : Callback<DetailResponse> {
            override fun onResponse(call: Call<DetailResponse>, response: Response<DetailResponse>) {
                isLoading.value = false
                if (response.isSuccessful) {
                    listDetail.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}