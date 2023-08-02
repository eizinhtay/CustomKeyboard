//package com.example.customkeyboard
//import android.content.Context
//import android.graphics.Typeface
//import android.inputmethodservice.InputMethodService
//import android.inputmethodservice.Keyboard
//import android.inputmethodservice.KeyboardView
//import android.inputmethodservice.KeyboardView.OnKeyboardActionListener
//import android.util.AttributeSet
//import android.view.KeyEvent
//import android.view.View
//import android.view.inputmethod.EditorInfo
//import android.view.inputmethod.InputMethodManager
//import android.widget.EditText
//import android.widget.TextView
//
//
//class CustomInputMethodService : InputMethodService() {
//
//    private lateinit var customKeyboard: CustomKeyboard
//
//    override fun onCreateInputView(): View {
//        val customKeyboardLayout = layoutInflater.inflate(R.layout.custom_keyboard, null) as CustomKeyboard
//
////        customKeyboardLayout.setPyidaungsuFont(customKeyboardLayout, this)
//        customKeyboard = customKeyboardLayout
//        customKeyboard.context = this
//        val keyboard = Keyboard(this,R.xml.mm_unicode)
//        customKeyboard.setKeyboard(keyboard,true)
//
//        return customKeyboardLayout
//    }
//
//    override fun onStartInputView(info: EditorInfo, restarting: Boolean) {
//        super.onStartInputView(info, restarting)
//
////        val currentInputConnection = info?.targetView as? EditText
////        customKeyboard.setInputConnection(currentInputConnection)
//    }
//
//    // Override other necessary methods as per your requirements
//
//    // Function to show the custom keyboard
//    private fun showCustomKeyboard() {
//        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.showInputMethodPicker()
//    }
//}
