package com.example.customkeyboard

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.customkeyboard.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // method to disable android soft keyboard
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
//            WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
//        )

        binding.btnSetting.setOnClickListener {
            // Open Language settings
            val intent = Intent()
            intent.component = ComponentName(
                "com.android.settings",
                "com.android.settings.Settings\$InputMethodAndLanguageSettingsActivity"
            )
            startActivity(intent)
        }

        binding.btnSelect.setOnClickListener {

            // Create an input method manager instance
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

           // Show the input method selection dialog
            inputMethodManager.showInputMethodPicker()

        }




    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == InputMethodManager.RESULT_SHOWN) {
            // Handle the result here
            Toast.makeText(this, "showed", Toast.LENGTH_SHORT).show()
        }
    }


}