package com.example.remotecontroljoystick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.example.remotecontroljoystick.databinding.ActivityMainBinding
import com.example.remotecontroljoystick.utilities.OnJoystickChange
import com.example.remotecontroljoystick.view.JoystickView
import com.example.remotecontroljoystick.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val vm = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)
        binding.viewModel = vm

        // assign joystick on change callback
        val joystick: JoystickView = findViewById(R.id.joystick)
        joystick.onChange = OnJoystickChange { aileron, elevator ->
            vm.aileron = aileron
            vm.elevator = elevator
        }
    }

    fun onConnectClick(view : View) {
        val ip : EditText = findViewById(R.id.ipEditText)
        val port : EditText = findViewById(R.id.portEditText)
        vm.startClientViewModel(ip.text.toString(), port.text.toString().toInt())
    }
}