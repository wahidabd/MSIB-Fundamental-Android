package com.wahidabd.githubapps.viewmodel

import androidx.lifecycle.*
import com.wahidabd.githubapps.core.SettingPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingThemeViewModel @Inject constructor(private val pref: SettingPreference): ViewModel() {

    fun getThemeSetting(): LiveData<Boolean> =
        pref.getThemeSetting().asLiveData()


    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }
}