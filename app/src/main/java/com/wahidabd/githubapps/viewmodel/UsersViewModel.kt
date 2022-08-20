package com.wahidabd.githubapps.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.githubapps.core.Status
import com.wahidabd.githubapps.data.model.User
import com.wahidabd.githubapps.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repo: UserRepository
): ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _list = MutableLiveData<List<User>>()
    val list: LiveData<List<User>> = _list

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun user(username: String){
        repo.detail(username).onEach { res ->
            when (res.status) {
                Status.LOADING -> _loading.postValue(true)
                Status.ERROR -> {
                    _loading.postValue(false)
                    _error.postValue(res.message.toString())
                }
                Status.SUCCESS -> {
                    _loading.postValue(false)
                    _user.postValue(res.data!!)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun list(){
        viewModelScope.launch(Dispatchers.IO){
            repo.users().collect { res ->
                when (res.status) {
                    Status.LOADING -> _loading.postValue(true)
                    Status.ERROR -> {
                        _loading.postValue(false)
                        _error.postValue(res.message.toString())
                    }
                    Status.SUCCESS -> {
                        _loading.postValue(false)
                        _list.postValue(res.data!!)
                    }
                }
            }
        }
    }

    fun search(q: String){
        viewModelScope.launch(Dispatchers.IO){
            repo.search(q).collect{ res ->
                when (res.status) {
                    Status.LOADING -> _loading.postValue(true)
                    Status.ERROR -> {
                        _loading.postValue(false)
                        _error.postValue(res.message.toString())
                    }
                    Status.SUCCESS -> {
                        _loading.postValue(false)
                        _list.postValue(res.data?.items)
                    }
                }
            }
        }
    }

}