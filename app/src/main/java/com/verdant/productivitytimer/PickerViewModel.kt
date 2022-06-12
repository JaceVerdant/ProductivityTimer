package com.verdant.productivitytimer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PickerViewModel : ViewModel() {
    private val minMinutes = 1
    private val maxMinutes = 60

    private val _currentMin = MutableLiveData(minMinutes)
    private val _lastMin = MutableLiveData(_currentMin.value!! - 1)
    private val _nextMin = MutableLiveData(_currentMin.value!! + 1)

    val currentMin: LiveData<Int> = _currentMin
    val lastMin: LiveData<Int> = _lastMin
    val nextMin: LiveData<Int> = _nextMin

    fun timerPlus() {
        if (_currentMin.value!! < maxMinutes) {
            _currentMin.value = _currentMin.value!! + 1
            _lastMin.value = _currentMin.value!! - 1
            _nextMin.value = _currentMin.value!! + 1
        }
    }

    fun timerMinus() {
        if (_currentMin.value!! > minMinutes) {
            _currentMin.value = _currentMin.value!! - 1
            _lastMin.value = _currentMin.value!! - 1
            _nextMin.value = _currentMin.value!! + 1
        }
    }
}
