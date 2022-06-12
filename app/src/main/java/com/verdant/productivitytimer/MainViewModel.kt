package com.verdant.productivitytimer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _currentMinutes = MutableLiveData(0)
    private val _currentSeconds = MutableLiveData(0)

    val currentSeconds: LiveData<Int> = _currentSeconds
    val currentMinutes: LiveData<Int> = _currentMinutes

    fun setCurrentMinutes(num: Int) {
        _currentMinutes.value = num
    }
}
