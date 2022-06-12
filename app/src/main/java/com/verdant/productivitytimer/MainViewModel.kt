package com.verdant.productivitytimer

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var isStarted = false

    private val _currentMinutes = MutableLiveData(0)
    private val _currentSeconds = MutableLiveData(0)

    val currentSeconds: LiveData<Int> = _currentSeconds
    val currentMinutes: LiveData<Int> = _currentMinutes

    private lateinit var timer: CountDownTimer

    fun setCurrentMinutes(num: Int) {
        _currentMinutes.value = num
    }

    fun start() {
        timer = object : CountDownTimer(_currentMinutes.value!! * 60000L, 1000) {
            override fun onTick(p0: Long) {
                if (_currentSeconds.value!! > 0) _currentSeconds.value =
                    _currentSeconds.value!! - 1
                else {
                    _currentMinutes.value = _currentMinutes.value!! - 1
                    _currentSeconds.value = 59
                }
            }

            override fun onFinish() {
                isStarted = false
            }
        }

        timer.start()
    }

    fun stop() {
        timer.cancel()
    }

    fun reset() {
        timer = object : CountDownTimer(0, 0) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
            }
        }
        _currentSeconds.value = 0
        _currentMinutes.value = 0
    }
}
