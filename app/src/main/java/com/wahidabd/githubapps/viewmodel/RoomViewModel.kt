package com.wahidabd.githubapps.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.githubapps.data.model.FavoriteData
import com.wahidabd.githubapps.data.repository.RoomRepository
import com.wahidabd.githubapps.data.service.room.FavoriteDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(private val repo: RoomRepository): ViewModel() {

    private val _list = MutableLiveData<List<FavoriteData>>()
    val list: LiveData<List<FavoriteData>> = _list

    private val _favorite = MutableLiveData<FavoriteData>()
    val favorite: LiveData<FavoriteData> = _favorite

    fun insert(data: FavoriteData){
        repo.insert(data)
    }

    fun list(){
        repo.list().onEach { res ->
            _list.postValue(res)
        }.launchIn(viewModelScope)
    }

    fun detail(login: String){
        repo.detail(login).onEach { res ->
            _favorite.postValue(res)
        }.launchIn(viewModelScope)
    }

    fun delete(login: String){
        repo.delete(login)
    }
}