package com.verdant.productivitytimer

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _currentMinutes = MutableLiveData(0)
    private val _currentSeconds = MutableLiveData(0)
    private val _currentProgress = MutableLiveData(0)
    private val _currentProgressMax = MutableLiveData(0)

    val currentSeconds: LiveData<Int> = _currentSeconds
    val currentMinutes: LiveData<Int> = _currentMinutes
    val currentProgress: LiveData<Int> = _currentProgress
    val currentProgressMax: LiveData<Int> = _currentProgressMax

    private lateinit var timer: CountDownTimer

    fun setCurrentMinutes(num: Int) {
        _currentMinutes.value = num
    }

    fun start() {
        val millis = if (_currentMinutes.value == 0) _currentSeconds.value!! * 1000L else _currentMinutes.value!! * 60000L
        _currentProgressMax.value = millis.toInt() / 1000
        timer = object : CountDownTimer(millis, 1000) {
            override fun onTick(p0: Long) {
                _currentProgress.value = _currentProgress.value!! + 1
                if (_currentSeconds.value!! > 0) _currentSeconds.value =
                    _currentSeconds.value!! - 1
                else {
                    _currentMinutes.value = _currentMinutes.value!! - 1
                    _currentSeconds.value = 59
                }
            }

            override fun onFinish() {
            }
        }

        timer.start()
    }

    fun stop() {
        timer.cancel()
    }

    fun reset() {
        timer.cancel()
        timer = object : CountDownTimer(0, 0) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
            }
        }

        _currentProgress.value = 0
        _currentSeconds.value = 0
        _currentMinutes.value = 0
    }
}
