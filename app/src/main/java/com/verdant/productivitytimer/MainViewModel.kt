package com.verdant.productivitytimer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _currentTimer = MutableLiveData(0)
    val currentTimer: LiveData<Int> = _currentTimer




    fun setCurrentMinutes(num: Int) {
        _currentTimer.value = num
    }

}
