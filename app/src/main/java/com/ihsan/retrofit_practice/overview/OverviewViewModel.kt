package com.ihsan.retrofit_practice.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ihsan.retrofit_practice.network.MarsApi
import com.ihsan.retrofit_practice.network.MarsPhoto
import kotlinx.coroutines.launch

class OverviewViewModel:ViewModel() {
    private val _status=MutableLiveData<String>()
    private val _photos=MutableLiveData<List<MarsPhoto>>()
    val status:LiveData<String> = _status
    val photos:LiveData<List<MarsPhoto>> = _photos

    init{
        getMarsPhotos()
    }
    private fun getMarsPhotos(){
        viewModelScope.launch{
            try {
                val resultList=MarsApi.retrofitService.getPhotos()
                _status.value="Success: ${resultList.size}"
            }
            catch (e: Exception){
                _status.value="Failure: ${e.message}"
            }
        }
    }
}