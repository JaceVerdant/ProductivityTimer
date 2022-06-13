package com.verdant.productivitytimer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        var isStarted = false

        launchPicker = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val num = it.data?.getStringExtra("key")?.toInt()
                if (num != null) {
                    viewModel.setCurrentMinutes(num)
                }
            }
        }

        viewModel.currentProgressMax.observe(this) {
            b.progressBar.max = it
        }

        viewModel.currentProgress.observe(this) {
            b.progressBar.progress = it
        }

        viewModel.currentMinutes.observe(this) {
            b.minutes.text = if (it < 10) "0$it" else "$it"
        }

        viewModel.currentSeconds.observe(this) {
            b.seconds.text = if (it < 10) "0$it" else "$it"
        }

        b.minutes.setOnClickListener {
            launchPicker.launch(Intent(this, PickerActivity::class.java))
        }

        b.seconds.setOnClickListener {
            launchPicker.launch(Intent(this, PickerActivity::class.java))
        }

        b.startBtn.setOnClickListener {

            if (!isStarted) {
                isStarted = true
                b.startBtn.setImageResource(R.drawable.pause_icon)
                viewModel.start()
            } else {
                isStarted = false
                b.startBtn.setImageResource(R.drawable.play_icon)
                viewModel.stop()
            }
        }

        b.stopBtn.setOnClickListener {
            viewModel.reset()
        }
    }
}
