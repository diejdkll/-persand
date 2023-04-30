package com.grapefruit.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grapefruit.myapplication.model.GithubDTO
import com.grapefruit.myapplication.model.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubViewModel : ViewModel(){
    private val githubAPI = RetrofitBuilder.githubAPI

    private var _gitInfoResponse =  MutableLiveData<GithubDTO>()
    val gitInfoResponse: LiveData<GithubDTO> get() = _gitInfoResponse

    fun getGitInfo(owner: String){
        githubAPI.getGitInfo(owner)
            .enqueue(object : Callback<GithubDTO> {
                override fun onResponse(call: Call<GithubDTO>, response: Response<GithubDTO>) {
                    _gitInfoResponse.value = response.body()
                    Log.d("test1", "${response.body()}")
                    Log.d("test2", "${_gitInfoResponse.value}")
                    Log.d("test3", "${gitInfoResponse.value}")
                }

                override fun onFailure(call: Call<GithubDTO>, t: Throwable) {

                }
            })
    }
}