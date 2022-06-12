package com.verdant.productivitytimer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.verdant.productivitytimer.databinding.ActivityPickerBinding

class PickerActivity : AppCompatActivity() {

    private lateinit var b: ActivityPickerBinding

    private val pickerViewModel by lazy {
        ViewModelProvider(this).get(PickerViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityPickerBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        pickerViewModel.currentMin.observe(this) {
            b.numCurrent.text = it.toString()
        }

        pickerViewModel.lastMin.observe(this) {
            b.numLast.text = if (it < 1) "-" else it.toString()
        }

        pickerViewModel.nextMin.observe(this) {
            b.numNext.text = if (it > 60) "-" else it.toString()
        }

        b.arrowTop.setOnClickListener { pickerViewModel.timerMinus() }

        b.arrowBottom.setOnClickListener { pickerViewModel.timerPlus() }

        b.cancel.setOnClickListener { finish() }

        b.confirm.setOnClickListener {
            val intent = Intent()
            intent.putExtra("key", b.numCurrent.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
