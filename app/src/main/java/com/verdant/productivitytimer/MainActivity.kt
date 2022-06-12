package com.verdant.productivitytimer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.verdant.productivitytimer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var b: ActivityMainBinding
    private lateinit var launchPicker: ActivityResultLauncher<Intent>

    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        launchPicker = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val num = it.data?.getStringExtra("key")?.toInt()
                if (num != null) {
                    viewModel.setCurrentMinutes(num)
                }
            }
        }

        viewModel.currentTimer.observe(this) {
            b.timer.text = if (it < 10) "0$it:00" else "$it:00"
        }

        b.timer.setOnClickListener {
            launchPicker.launch(Intent(this, PickerActivity::class.java))
        }
    }
}
