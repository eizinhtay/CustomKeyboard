package com.example.customkeyboard

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.FontsContract
import android.provider.Settings
import android.util.Log
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.provider.FontRequest
import androidx.core.provider.FontsContractCompat
import androidx.core.view.WindowCompat
import com.example.customkeyboard.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val request = FontRequest(
            "com.example.fontprovider.authority",
            "com.example.fontprovider",
            "my font",
            R.array.certs
        )
        val callback = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            object : FontsContractCompat.FontRequestCallback() {

                override fun onTypefaceRetrieved(typeface: Typeface) {
                    // Your code to use the font goes here.

                }

                override fun onTypefaceRequestFailed(reason: Int) {
                    // Your code to deal with the failure goes here.
                }
            }
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        FontsContractCompat.requestFont(this, request,  callback, Handler())
        // method to disable android soft keyboard
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
//            WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
//        )

        binding.btnSetting.setOnClickListener {
            // Open Language settings
//            val intent = Intent()
//            intent.component = ComponentName(
//                "com.android.settings",
//                "com.android.settings.Settings\$InputMethodAndLanguageSettingsActivity"
//            )
            val intent = Intent("android.settings.INPUT_METHOD_SETTINGS")
            startActivity(intent)
           // startActivity(intent)
        }

        binding.btnSelect.setOnClickListener {

            // Create an input method manager instance
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

           // Show the input method selection dialog
            inputMethodManager.showInputMethodPicker()
            Log.d("btnSelect","${binding.btnSelect.text}")

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